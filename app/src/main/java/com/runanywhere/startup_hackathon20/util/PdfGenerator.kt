package com.runanywhere.startup_hackathon20.util

import android.content.Context
import android.graphics.Paint
import android.graphics.Typeface
import android.graphics.pdf.PdfDocument
import android.os.Environment
import androidx.core.content.FileProvider
import com.runanywhere.startup_hackathon20.rights.RightItem
import java.io.File
import java.io.FileOutputStream

object PdfGenerator {

    fun generateRightsPdf(context: Context, right: RightItem): File? {
        return try {

            val document = PdfDocument()

            val titlePaint = Paint().apply {
                textSize = 22f
                isFakeBoldText = true
                typeface = Typeface.DEFAULT_BOLD
            }

            val bodyPaint = Paint().apply {
                textSize = 16f
                isAntiAlias = true
            }

            val pageWidth = 595      // A4 width
            val pageHeight = 842     // A4 height
            val margin = 40f
            val lineSpacing = 26f

            var y = margin + 30f
            var pageNumber = 1

            fun newPage(): PdfDocument.Page {
                val pageInfo = PdfDocument.PageInfo.Builder(pageWidth, pageHeight, pageNumber).create()
                val page = document.startPage(pageInfo)
                y = margin + 30f
                pageNumber++
                return page
            }

            var page = newPage()

            // Draw Title
            page.canvas.drawText(right.title, margin, y, titlePaint)
            y += 40f

            // FULL-TEXT for PDF
            val paragraphs = right.fullText.split("\n")

            for (para in paragraphs) {

                if (para.isBlank()) {
                    y += lineSpacing
                    continue
                }

                val wrappedLines = wrapText(para, bodyPaint, pageWidth - margin * 2)

                for (line in wrappedLines) {

                    if (y > pageHeight - margin - 40) {
                        // Start new page
                        document.finishPage(page)
                        page = newPage()
                    }

                    page.canvas.drawText(line, margin, y, bodyPaint)
                    y += lineSpacing
                }
            }

            document.finishPage(page)

            // Save PDF to app storage
            val folder = File(
                context.getExternalFilesDir(Environment.DIRECTORY_DOCUMENTS),
                "PocketLawyer"
            )
            if (!folder.exists()) folder.mkdirs()

            val file = File(folder, "${right.title}.pdf")
            document.writeTo(FileOutputStream(file))
            document.close()
            file

        } catch (e: Exception) {
            e.printStackTrace()
            null
        }
    }

    private fun wrapText(text: String, paint: Paint, maxWidth: Float): List<String> {
        val words = text.split(" ")
        val lines = mutableListOf<String>()
        var current = ""

        for (word in words) {
            val candidate = if (current.isEmpty()) word else "$current $word"

            if (paint.measureText(candidate) <= maxWidth) {
                current = candidate
            } else {
                lines.add(current)
                current = word
            }
        }

        if (current.isNotEmpty()) lines.add(current)

        return lines
    }

    fun getPdfUri(context: Context, file: File) =
        FileProvider.getUriForFile(context, "${context.packageName}.provider", file)
}
