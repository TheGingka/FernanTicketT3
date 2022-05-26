package models.comunicaciones;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.IOException;
import java.util.Properties;

public class Gmail {
    public static boolean enviaMail(String destino, String mensaje, String asunto){

        boolean resultado;
        resultado = false;

        String emisor = "dam6@carlosprofe.es";
        String username = "dam6@carlosprofe.es";
        String password = "Olivo.2022";

        String host = "ssl0.ovh.net";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(username, password);
            }
        });

        try{
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(emisor));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destino));

            message.setSubject(asunto);

            message.setContent(mensaje, "text/html; charset=utf-8");

            Transport.send(message);
            resultado = true;
        }catch(MessagingException e){
            throw new RuntimeException(e);
        }
        return resultado;
    }

    public static String mensaje(int token){
        return "<!DOCTYPE HTML PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional //EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\n" +
                "<html xmlns=\"http://www.w3.org/1999/xhtml\" xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:o=\"urn:schemas-microsoft-com:office:office\">\n" +
                "<head>\n" +
                "<!--[if gte mso 9]>\n" +
                "<xml>\n" +
                "  <o:OfficeDocumentSettings>\n" +
                "    <o:AllowPNG/>\n" +
                "    <o:PixelsPerInch>96</o:PixelsPerInch>\n" +
                "  </o:OfficeDocumentSettings>\n" +
                "</xml>\n" +
                "<![endif]-->\n" +
                "  <meta http-equiv=\"Content-Type\" content=\"text/html; charset=UTF-8\">\n" +
                "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "  <meta name=\"x-apple-disable-message-reformatting\">\n" +
                "  <!--[if !mso]><!--><meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\"><!--<![endif]-->\n" +
                "  <title></title>\n" +
                "  \n" +
                "    <style type=\"text/css\">\n" +
                "      table, td { color: #000000; } a { color: #01499d; text-decoration: underline; } @media (max-width: 480px) { #u_content_heading_1 .v-container-padding-padding { padding: 20px !important; } #u_content_heading_1 .v-font-size { font-size: 23px !important; } #u_content_text_2 .v-container-padding-padding { padding: 40px 30px 50px 15px !important; } #u_content_button_1 .v-size-width { width: 81% !important; } }\n" +
                "@media only screen and (min-width: 620px) {\n" +
                "  .u-row {\n" +
                "    width: 600px !important;\n" +
                "  }\n" +
                "  .u-row .u-col {\n" +
                "    vertical-align: top;\n" +
                "  }\n" +
                "\n" +
                "  .u-row .u-col-100 {\n" +
                "    width: 600px !important;\n" +
                "  }\n" +
                "\n" +
                "}\n" +
                "\n" +
                "@media (max-width: 620px) {\n" +
                "  .u-row-container {\n" +
                "    max-width: 100% !important;\n" +
                "    padding-left: 0px !important;\n" +
                "    padding-right: 0px !important;\n" +
                "  }\n" +
                "  .u-row .u-col {\n" +
                "    min-width: 320px !important;\n" +
                "    max-width: 100% !important;\n" +
                "    display: block !important;\n" +
                "  }\n" +
                "  .u-row {\n" +
                "    width: calc(100% - 40px) !important;\n" +
                "  }\n" +
                "  .u-col {\n" +
                "    width: 100% !important;\n" +
                "  }\n" +
                "  .u-col > div {\n" +
                "    margin: 0 auto;\n" +
                "  }\n" +
                "}\n" +
                "body {\n" +
                "  margin: 0;\n" +
                "  padding: 0;\n" +
                "}\n" +
                "\n" +
                "table,\n" +
                "tr,\n" +
                "td {\n" +
                "  vertical-align: top;\n" +
                "  border-collapse: collapse;\n" +
                "}\n" +
                "\n" +
                "p {\n" +
                "  margin: 0;\n" +
                "}\n" +
                "\n" +
                ".ie-container table,\n" +
                ".mso-container table {\n" +
                "  table-layout: fixed;\n" +
                "}\n" +
                "\n" +
                "* {\n" +
                "  line-height: inherit;\n" +
                "}\n" +
                "\n" +
                "a[x-apple-data-detectors='true'] {\n" +
                "  color: inherit !important;\n" +
                "  text-decoration: none !important;\n" +
                "}\n" +
                "\n" +
                "</style>\n" +
                "  \n" +
                "  \n" +
                "\n" +
                "<!--[if !mso]><!--><link href=\"https://fonts.googleapis.com/css?family=Raleway:400,700&display=swap\" rel=\"stylesheet\" type=\"text/css\"><link href=\"https://fonts.googleapis.com/css?family=Rubik:400,700&display=swap\" rel=\"stylesheet\" type=\"text/css\"><!--<![endif]-->\n" +
                "\n" +
                "</head>\n" +
                "\n" +
                "<body class=\"clean-body u_body\" style=\"margin: 0;padding: 0;-webkit-text-size-adjust: 100%;background-color: #e7e7e7;color: #000000\">\n" +
                "  <!--[if IE]><div class=\"ie-container\"><![endif]-->\n" +
                "  <!--[if mso]><div class=\"mso-container\"><![endif]-->\n" +
                "  <table style=\"border-collapse: collapse;table-layout: fixed;border-spacing: 0;mso-table-lspace: 0pt;mso-table-rspace: 0pt;vertical-align: top;min-width: 320px;Margin: 0 auto;background-color: #e7e7e7;width:100%\" cellpadding=\"0\" cellspacing=\"0\">\n" +
                "  <tbody>\n" +
                "  <tr style=\"vertical-align: top\">\n" +
                "    <td style=\"word-break: break-word;border-collapse: collapse !important;vertical-align: top\">\n" +
                "    <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td align=\"center\" style=\"background-color: #e7e7e7;\"><![endif]-->\n" +
                "    \n" +
                "\n" +
                "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #01499d;\">\n" +
                "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
                "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #01499d;\"><![endif]-->\n" +
                "      \n" +
                "<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n" +
                "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
                "  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n" +
                "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n" +
                "  \n" +
                "<table id=\"u_content_heading_1\" style=\"font-family:'Rubik',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "  <tbody>\n" +
                "    <tr>\n" +
                "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:20px 25px;font-family:'Rubik',sans-serif;\" align=\"left\">\n" +
                "        \n" +
                "  <h1 class=\"v-font-size\" style=\"margin: 0px; color: #ffffff; line-height: 140%; text-align: center; word-wrap: break-word; font-weight: normal; font-family: 'Raleway',sans-serif; font-size: 27px;\">\n" +
                "    FernanTicket\n" +
                "  </h1>\n" +
                "\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody>\n" +
                "</table>\n" +
                "\n" +
                "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
                "  </div>\n" +
                "</div>\n" +
                "<!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #f5f5f5;\">\n" +
                "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
                "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #f5f5f5;\"><![endif]-->\n" +
                "      \n" +
                "<!--[if (mso)|(IE)]><td align=\"center\" width=\"598\" style=\"width: 598px;padding: 0px;border-top: 1px solid #CCC;border-left: 1px solid #CCC;border-right: 1px solid #CCC;border-bottom: 1px solid #CCC;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n" +
                "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
                "  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n" +
                "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 1px solid #CCC;border-left: 1px solid #CCC;border-right: 1px solid #CCC;border-bottom: 1px solid #CCC;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n" +
                "  \n" +
                "<table id=\"u_content_text_2\" style=\"font-family:'Rubik',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "  <tbody>\n" +
                "    <tr>\n" +
                "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:40px 50px 50px;font-family:'Rubik',sans-serif;\" align=\"left\">\n" +
                "        \n" +
                "  <div style=\"color: #5c5c5c; line-height: 170%; text-align: left; word-wrap: break-word;\">\n" +
                "    <p style=\"font-size: 14px; line-height: 170%;\">Hola,</p>\n" +
                "<p style=\"font-size: 14px; line-height: 170%;\">&nbsp;</p>\n" +
                "<p style=\"font-size: 14px; line-height: 170%;\">Hemos recibido un registro con este correo.</p>\n" +
                "<p style=\"font-size: 14px; line-height: 170%;\">&nbsp;</p>\n" +
                "<p style=\"font-size: 14px; line-height: 170%;\">Para poder registrarte correctamente tienes que utilizar el siguiente token que aparece justo debajo:</p>\n" +
                "  </div>\n" +
                "\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody>\n" +
                "</table>\n" +
                "\n" +
                "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
                "  </div>\n" +
                "</div>\n" +
                "<!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #01499d;\">\n" +
                "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
                "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #01499d;\"><![endif]-->\n" +
                "      \n" +
                "<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n" +
                "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
                "  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n" +
                "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n" +
                "  \n" +
                "<table id=\"u_content_button_1\" style=\"font-family:'Rubik',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "  <tbody>\n" +
                "    <tr>\n" +
                "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:35px 10px;font-family:'Rubik',sans-serif;\" align=\"left\">\n" +
                "        \n" +
                "<div align=\"center\">\n" +
                "  <!--[if mso]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"border-spacing: 0; border-collapse: collapse; mso-table-lspace:0pt; mso-table-rspace:0pt;font-family:'Rubik',sans-serif;\"><tr><td style=\"font-family:'Rubik',sans-serif;\" align=\"center\"><v:roundrect xmlns:v=\"urn:schemas-microsoft-com:vml\" xmlns:w=\"urn:schemas-microsoft-com:office:word\" href=\"\" style=\"height:51px; v-text-anchor:middle; width:267px;\" arcsize=\"0%\" strokecolor=\"#ffffff\" strokeweight=\"3px\" fillcolor=\"#01499d\"><w:anchorlock/><center style=\"color:#FFFFFF;font-family:'Rubik',sans-serif;\"><![endif]-->\n" +
                "    <a target=\"_blank\" class=\"v-size-width\" style=\"box-sizing: border-box;display: inline-block;font-family:'Rubik',sans-serif;text-decoration: none;-webkit-text-size-adjust: none;text-align: center;color: #FFFFFF; background-color: #01499d; border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px; width:47%; max-width:100%; overflow-wrap: break-word; word-break: break-word; word-wrap:break-word; mso-border-alt: none;border-top-color: #ffffff; border-top-style: solid; border-top-width: 3px; border-left-color: #ffffff; border-left-style: solid; border-left-width: 3px; border-right-color: #ffffff; border-right-style: solid; border-right-width: 3px; border-bottom-color: #ffffff; border-bottom-style: solid; border-bottom-width: 3px;\">\n" +
                "      <span style=\"display:block;padding:16px 20px;line-height:120%;\"><strong><span style=\"font-size: 16px; line-height: 19.2px; font-family: Raleway, sans-serif;\">"+ token +"</span></strong></span>\n" +
                "    </a>\n" +
                "  <!--[if mso]></center></v:roundrect></td></tr></table><![endif]-->\n" +
                "</div>\n" +
                "\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody>\n" +
                "</table>\n" +
                "\n" +
                "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
                "  </div>\n" +
                "</div>\n" +
                "<!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "\n" +
                "<div class=\"u-row-container\" style=\"padding: 0px;background-color: transparent\">\n" +
                "  <div class=\"u-row\" style=\"Margin: 0 auto;min-width: 320px;max-width: 600px;overflow-wrap: break-word;word-wrap: break-word;word-break: break-word;background-color: #1a2e35;\">\n" +
                "    <div style=\"border-collapse: collapse;display: table;width: 100%;background-color: transparent;\">\n" +
                "      <!--[if (mso)|(IE)]><table width=\"100%\" cellpadding=\"0\" cellspacing=\"0\" border=\"0\"><tr><td style=\"padding: 0px;background-color: transparent;\" align=\"center\"><table cellpadding=\"0\" cellspacing=\"0\" border=\"0\" style=\"width:600px;\"><tr style=\"background-color: #1a2e35;\"><![endif]-->\n" +
                "      \n" +
                "<!--[if (mso)|(IE)]><td align=\"center\" width=\"600\" style=\"width: 600px;padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\" valign=\"top\"><![endif]-->\n" +
                "<div class=\"u-col u-col-100\" style=\"max-width: 320px;min-width: 600px;display: table-cell;vertical-align: top;\">\n" +
                "  <div style=\"width: 100% !important;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\">\n" +
                "  <!--[if (!mso)&(!IE)]><!--><div style=\"padding: 0px;border-top: 0px solid transparent;border-left: 0px solid transparent;border-right: 0px solid transparent;border-bottom: 0px solid transparent;border-radius: 0px;-webkit-border-radius: 0px; -moz-border-radius: 0px;\"><!--<![endif]-->\n" +
                "  \n" +
                "<table style=\"font-family:'Rubik',sans-serif;\" role=\"presentation\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" border=\"0\">\n" +
                "  <tbody>\n" +
                "    <tr>\n" +
                "      <td class=\"v-container-padding-padding\" style=\"overflow-wrap:break-word;word-break:break-word;padding:10px 44px 35px;font-family:'Rubik',sans-serif;\" align=\"left\">\n" +
                "        \n" +
                "  <div style=\"color: #ecf0f1; line-height: 180%; text-align: center; word-wrap: break-word;\">\n" +
                "    <p style=\"font-size: 14px; line-height: 180%;\"><span style=\"font-family: Raleway, sans-serif;\">2022 - FERNANTICKET - COPYRIGHT</span></p>\n" +
                "  </div>\n" +
                "\n" +
                "      </td>\n" +
                "    </tr>\n" +
                "  </tbody>\n" +
                "</table>\n" +
                "\n" +
                "  <!--[if (!mso)&(!IE)]><!--></div><!--<![endif]-->\n" +
                "  </div>\n" +
                "</div>\n" +
                "<!--[if (mso)|(IE)]></td><![endif]-->\n" +
                "      <!--[if (mso)|(IE)]></tr></table></td></tr></table><![endif]-->\n" +
                "    </div>\n" +
                "  </div>\n" +
                "</div>\n" +
                "\n" +
                "\n" +
                "    <!--[if (mso)|(IE)]></td></tr></table><![endif]-->\n" +
                "    </td>\n" +
                "  </tr>\n" +
                "  </tbody>\n" +
                "  </table>\n" +
                "  <!--[if mso]></div><![endif]-->\n" +
                "  <!--[if IE]></div><![endif]-->\n" +
                "</body>\n" +
                "\n" +
                "</html>";
    }

    public static boolean enviaMailExcel(String destino, String mensaje, String asunto){

        boolean resultado;
        resultado = false;

        String emisor = "dam6@carlosprofe.es";
        String username = "dam6@carlosprofe.es";
        String password = "Olivo.2022";

        String host = "ssl0.ovh.net";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "false");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props, new javax.mail.Authenticator(){
            @Override
            protected PasswordAuthentication getPasswordAuthentication(){
                return new PasswordAuthentication(username, password);
            }
        });

        try{
            Message message = new MimeMessage(session);

            MimeBodyPart mimeBodyPartAdjunto = new MimeBodyPart();
            mimeBodyPartAdjunto.attachFile("./Excel.xlsx");

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(mimeBodyPartAdjunto);


            message.setFrom(new InternetAddress(emisor));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse(destino));

            message.setSubject(asunto);
            message.setContent(mensaje, "text/html; charset=utf-8");
            message.setContent(multipart);

            Transport.send(message);
            resultado = true;
        }catch(MessagingException | IOException e){
            throw new RuntimeException(e);
        }
        return resultado;
    }

}
