package com.google.mlkit.vision.demo
import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View

class CustomRectangleWithRoundedCornersCutoutView @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {

    private val edgeLength = 100f
    private val cornerRadius = 20f
    private val qrScannerWidth = 500f
    private val qrScannerHeight = 500f

    /*Determines vertical position of the center point in the scanner cutout shape
      0f -> Center of scanner cutout shape will be at the top of the Canvas
     0.5f -> Center of scanner cutout shape will be at the middle of the Canvas
     1f -> Center of scanner cutout shape will be at the bottom of the Canvas */
    private val verticalOffset = 0.5f

    /*Determines horizontal position of the center point in the scanner cutout shape
  0f -> Center of scanner cutout shape will be at the top of the Canvas
 0.5f -> Center of scanner cutout shape will be at the middle of the Canvas
 1f -> Center of scanner cutout shape will be at the bottom of the Canvas */
    private val horizontalOffset = 0.5f

    // Edges of QR scanner
    public var xAxisLeftEdge = 0f
    public var xAxisRightEdge = 0f
    public var yAxisTopEdge = 0f
    public var yAxisBottomEdge = 0f

    private val frameStrokeWidth = 4f.toFloat()

    private val backgroundPaint = Paint().apply {
        setARGB(80, 0, 0, 0)
    }

    private val transparentPaint = Paint().apply {
        color = Color.TRANSPARENT
    }

    private val framePaint = Paint().apply {
        isAntiAlias = true
        color = Color.GREEN
        strokeWidth = frameStrokeWidth
        style = Paint.Style.STROKE
    }

    private lateinit var backgroundShape: Path
    private lateinit var qrScannerShape: Path
    private lateinit var qrScannerCornersShape: Path

    private fun createBackgroundPath() = Path().apply {
        lineTo(right.toFloat(), 0f)
        lineTo(right.toFloat(), bottom.toFloat())
        lineTo(0f, bottom.toFloat())
        lineTo(0f, 0f)
        fillType = Path.FillType.EVEN_ODD
    }

    private fun createQrPath() = Path().apply {
        moveTo(xAxisLeftEdge, yAxisTopEdge + cornerRadius)
        quadTo(xAxisLeftEdge, yAxisTopEdge, xAxisLeftEdge + cornerRadius, yAxisTopEdge)

        lineTo(xAxisRightEdge - cornerRadius, yAxisTopEdge)
        quadTo(xAxisRightEdge, yAxisTopEdge, xAxisRightEdge, yAxisTopEdge + cornerRadius)

        lineTo(xAxisRightEdge, yAxisBottomEdge - cornerRadius)
        quadTo(xAxisRightEdge, yAxisBottomEdge, xAxisRightEdge - cornerRadius, yAxisBottomEdge)

        lineTo(xAxisLeftEdge + cornerRadius, yAxisBottomEdge)
        quadTo(xAxisLeftEdge, yAxisBottomEdge, xAxisLeftEdge, yAxisBottomEdge - cornerRadius)
        lineTo(xAxisLeftEdge, yAxisTopEdge + cornerRadius)
        fillType = Path.FillType.EVEN_ODD
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        canvas?.apply {
            xAxisLeftEdge = width * horizontalOffset - qrScannerWidth / 2f
            xAxisRightEdge = width * horizontalOffset + qrScannerWidth / 2f
            yAxisTopEdge = height * verticalOffset - qrScannerHeight / 2f
            yAxisBottomEdge = height * verticalOffset + qrScannerHeight / 2f

            backgroundShape = createBackgroundPath()
            qrScannerShape = createQrPath()
            qrScannerCornersShape = createCutoutWithCorners()
            backgroundShape.addPath(qrScannerShape)

            drawPath(backgroundShape, backgroundPaint)
            drawPath(qrScannerShape, transparentPaint)
            drawPath(qrScannerCornersShape, framePaint)
        }
    }

    private fun createCutoutWithCorners() = Path().apply {
        moveTo(xAxisLeftEdge, yAxisTopEdge + edgeLength)
        lineTo(xAxisLeftEdge, yAxisTopEdge + cornerRadius)

        moveTo(xAxisLeftEdge, yAxisTopEdge + cornerRadius)
        quadTo(xAxisLeftEdge, yAxisTopEdge, xAxisLeftEdge + cornerRadius, yAxisTopEdge)
        lineTo(xAxisLeftEdge + edgeLength, yAxisTopEdge)

        moveTo(xAxisRightEdge - edgeLength, yAxisTopEdge)
        lineTo(xAxisRightEdge - cornerRadius, yAxisTopEdge)

        moveTo(xAxisRightEdge - cornerRadius, yAxisTopEdge)
        quadTo(xAxisRightEdge, yAxisTopEdge, xAxisRightEdge, yAxisTopEdge + cornerRadius)
        lineTo(xAxisRightEdge, yAxisTopEdge + edgeLength)

        moveTo(xAxisRightEdge, yAxisBottomEdge - edgeLength)
        lineTo(xAxisRightEdge, yAxisBottomEdge - cornerRadius)

        moveTo(xAxisRightEdge, yAxisBottomEdge - cornerRadius)
        quadTo(xAxisRightEdge, yAxisBottomEdge, xAxisRightEdge - cornerRadius, yAxisBottomEdge)
        lineTo(xAxisRightEdge - edgeLength, yAxisBottomEdge)

        moveTo(xAxisLeftEdge + edgeLength, yAxisBottomEdge)
        lineTo(xAxisLeftEdge + cornerRadius, yAxisBottomEdge)

        moveTo(xAxisLeftEdge + cornerRadius, yAxisBottomEdge)
        quadTo(xAxisLeftEdge, yAxisBottomEdge, xAxisLeftEdge, yAxisBottomEdge - cornerRadius)
        lineTo(xAxisLeftEdge, yAxisBottomEdge - edgeLength)
    }

}