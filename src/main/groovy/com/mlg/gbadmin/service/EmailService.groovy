package com.mlg.gbadmin.service

import com.mlg.gbadmin.dto.email.EmailRequestDto
import groovy.transform.TupleConstructor
import jakarta.mail.MessagingException
import jakarta.mail.internet.MimeMessage
import org.springframework.beans.factory.annotation.Value
import org.springframework.core.io.ByteArrayResource
import org.springframework.core.io.InputStreamResource
import org.springframework.core.io.Resource
import org.springframework.mail.javamail.JavaMailSender
import org.springframework.mail.javamail.MimeMessageHelper
import org.springframework.stereotype.Service
import org.thymeleaf.TemplateEngine
import org.thymeleaf.context.Context

import static com.mlg.gbadmin.utils.QrCodeUtils.generateQRCodeImageAsByteArray

@Service
@TupleConstructor(includeFields = true, defaults = false, excludes = ["fromMail"])
class EmailService {

    @Value('${spring.mail.username}')
    private String fromMail

    private final JavaMailSender mailSender
    private final TemplateEngine templateEngine

    void sendMail(EmailRequestDto request) throws MessagingException {
        MimeMessage mimeMessage = mailSender.createMimeMessage()
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true)

        mimeMessageHelper.from = fromMail
        mimeMessageHelper.to = request.emailTo
        mimeMessageHelper.subject = request.subject

        if(request.isHtml) {
            Context context = new Context()
            try {
                byte[] qrCodeBytes = generateQRCodeImageAsByteArray(request.message)
                File qrCodeFile = File.createTempFile("qrCode", ".png")
                qrCodeFile.deleteOnExit()

                FileOutputStream fos = new FileOutputStream(qrCodeFile)
                fos.write(qrCodeBytes)
                fos.close()

                String imageName = qrCodeFile.getName()
                mimeMessageHelper.addAttachment(imageName, qrCodeFile)

                context.setVariable("content", "Please find the attached QR code.")
                String processedString = templateEngine.process("gbcard", context)

                mimeMessageHelper.setText(processedString, true)
            } catch (IOException | MessagingException e) {
                throw new RuntimeException("Failed to send email with QR code", e)
            }

        } else {
            mimeMessageHelper.setText(request.getMessage(), false)
        }

        mailSender.send(mimeMessage)
    }

}
