package com.example.piksel;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ImageView imageView;
    private TextView textView;
    private Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        imageView = findViewById(R.id.imageView);
        button = findViewById(R.id.button);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                BitmapDrawable mydrawable = (BitmapDrawable) imageView.getDrawable();
                Bitmap b = mydrawable.getBitmap();
                b = doInvert(b);

                imageView.setImageBitmap(b);
                textView.setText("jk");
                Toast.makeText(getApplicationContext(),
                        "Инвертировано", Toast.LENGTH_LONG).show();
            }
        });
    }
    public static Bitmap doInvert(Bitmap src) {
        // Создадим новый bitmap с теми же настройками, как у источника
        Bitmap bmOut = Bitmap.createBitmap(src.getWidth(), src.getHeight(), src.getConfig());
        // информация о цветах
        int A , R, G, B;
        int pixelColor;
        // размер изображения
        int height = src.getHeight();
        int width = src.getWidth();

        // проходим через каждый пиксель
        for (int y = 0; y < height; y++)
        {
            for (int x = 0; x < width; x++)
            {
                // работаем с отдельным пикселем
                pixelColor = src.getPixel(x, y);
                // сохраняем alpha-канал
                A = Color.alpha(pixelColor);
                // инвертируем байт каждого R/G/B канала
                R = 255 - Color.red(pixelColor);
                G = 255 - Color.green(pixelColor);
                B = 255 - Color.blue(pixelColor);
                // полученный результа записываем в этот же пиксель
                bmOut.setPixel(x, y, Color.argb(A, R, G, B));
                if((R > 200 && R < 228) && (G > 160 && G < 190) && (B > 213 && B < 251)){
                    bmOut.setPixel(x, y, Color.argb(A, R, G, B));
                }
            }
        }
        // Получаем новое изображение
        return bmOut;
    }
}