/*
 * Copyright 2020 Google LLC. All rights reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

 package com.google.mlkit.vision.demo;

 import static java.lang.Math.max;
 import static java.lang.Math.min;
 
 import android.graphics.Canvas;
 import android.graphics.Color;
 import android.graphics.Paint;
 import android.graphics.RectF;
 import com.google.mlkit.vision.demo.GraphicOverlay;
 import com.google.mlkit.vision.demo.GraphicOverlay.Graphic;
 
 /** Graphic instance for rendering Barcode position and content information in an overlay view. */
 public class Overlay extends Graphic {
 
   private static final int TEXT_COLOR = Color.BLACK;
   private static final int MARKER_COLOR = Color.WHITE;
   private static final float TEXT_SIZE = 54.0f;
   private static final float STROKE_WIDTH = 4.0f;
 
   private final Paint rectPaint;
 
   Overlay(GraphicOverlay overlay) {
     super(overlay);
 
     rectPaint = new Paint();
     rectPaint.setColor(MARKER_COLOR);
     rectPaint.setStyle(Paint.Style.STROKE);
     rectPaint.setStrokeWidth(STROKE_WIDTH);
   }
 
   /**
    * Draws the barcode block annotations for position, size, and raw value on the supplied canvas.
    */
   @Override
   public void draw(Canvas canvas) {
 
     canvas.drawRect(100, 100, 500, 500, rectPaint);
   }
 }
 