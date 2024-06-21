package com.mlg.gbadmin.utils

import com.google.zxing.BarcodeFormat
import com.google.zxing.client.j2se.MatrixToImageWriter
import com.google.zxing.common.BitMatrix
import com.google.zxing.qrcode.QRCodeWriter

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

class QrCodeUtils {

    static byte[] generateQRCodeImageAsByteArray(String studentNumber) throws IOException {
        QRCodeWriter code = new QRCodeWriter()
        BitMatrix bitMatrix = code.encode(studentNumber.toString(), BarcodeFormat.QR_CODE, 200, 200)
        BufferedImage image = MatrixToImageWriter.toBufferedImage(bitMatrix)
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()
        ImageIO.write(image, "png", byteArrayOutputStream)
        byteArrayOutputStream.toByteArray()
    }
}
