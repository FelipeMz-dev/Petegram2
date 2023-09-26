package com.mz_dev.petagram.mailHelper;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.mz_dev.petagram.R;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class SendMailTask extends AsyncTask<Void, Void, Boolean> {

    private final Context context;
    private final String email;
    private final String name;
    private final String message;
    private Session session;
    private ProgressDialog progressDialog;

    public SendMailTask (Context context, String email, String name, String message) {
        this.context = context;
        this.email = email;
        this.name = name;
        this.message = message;
    }

    @Override
    protected void onPreExecute () {
        super.onPreExecute ();
        progressDialog = ProgressDialog.show (context, context.getString(R.string.sending_comment),
                context.getString(R.string.please_wait), false, false);
    }

    @Override
    protected void onPostExecute (Boolean aBoolean) {
        super.onPostExecute (aBoolean);
        progressDialog.dismiss ();
        if (aBoolean) {
            Toast.makeText (context, context.getString(R.string.comment_sent), Toast.LENGTH_SHORT).show ();
        } else {
            Toast.makeText (context, context.getString(R.string.error_sending_comment), Toast.LENGTH_SHORT).show ();
        }
    }

    @Override
    protected Boolean doInBackground (Void... params) {
        //SMTP Properties
        Properties properties = new Properties ();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");
        String senderEmail = context.getString(R.string.emailAuthApp);
        String senderPass = context.getString(R.string.passAuthApp);

        Authenticator authenticator = new MyAuthenticator(senderEmail, senderPass);
        session = Session.getInstance (properties, authenticator);

        try {
            //SMTP send
            String messageBody = "By: " + name +
                    "\nEmail: " + email +
                    "\nComment:\n" + message;
            Message mimeMessage = createMessage(senderEmail, senderEmail, messageBody);
            Transport.send (mimeMessage);
            String messageBodyConfirm = "Hello " + name +
                    ", we have received your comment. Thanks for your time";
            Message confirmMessage = createMessage(senderEmail, email, messageBodyConfirm);
            Transport.send(confirmMessage);
            return true;
        } catch (MessagingException e) {
            e.printStackTrace ();
            return false;
        }

    }

    private Message createMessage(String senderEmail, String recipientEmail, String messageBody) throws MessagingException {
        Message mimeMessage = new MimeMessage (session);
            mimeMessage.setFrom (new InternetAddress(senderEmail));
            mimeMessage.addRecipient (Message.RecipientType.TO, new InternetAddress (recipientEmail));
            mimeMessage.setSubject ("User Contact");
            mimeMessage.setText (messageBody);
        return mimeMessage;
    }
}
