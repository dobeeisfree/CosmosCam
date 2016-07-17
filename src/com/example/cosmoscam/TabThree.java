package com.example.cosmoscam;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Random;

import com.example.cosmoscam.TabTwoA.onAdd;
import com.example.cosmoscam.TabTwoA.onOpen;
import com.example.cosmoscam.TabTwoA.onResult;
import com.example.cosmoscam.TabTwoA.onSave;
import com.example.cosmoscam.TabTwoB.onBlur;
import com.example.cosmoscam.TabTwoB.onCam;
import com.example.cosmoscam.TabTwoB.onCartoon;
import com.example.cosmoscam.TabTwoB.onCopy;
import com.example.cosmoscam.TabTwoB.onEdges;
import com.example.cosmoscam.TabTwoB.onFlip;
import com.example.cosmoscam.TabTwoB.onInvert;
import com.example.cosmoscam.TabTwoB.onMirror;
import com.example.cosmoscam.TabTwoB.onRepeat;
import com.example.cosmoscam.TabTwoB.onSpace;

import android.widget.AdapterView.OnItemClickListener;
import android.R.color;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RadialGradient;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader.TileMode;
import android.view.View;


public class TabThree extends Activity {
	ImageView btSave, btAdd;
	ImageView iv;
	Bitmap input, output;
	EditText edit_name;
	
	ImageView btCopy, btInvert, btFlip, btMirror, 
				btRepeat, btBlur, btEdges, btCartoon, btSpace,
				btBlackOut;

	int click=0;
	

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.tab3);
	  
	    // TODO Auto-generated method stub
	    iv = (ImageView)findViewById(R.id.imageView10);
	    btAdd = (ImageView)findViewById(R.id.imageView12);
	    btAdd.setOnClickListener(new onAdd());
	    btSave = (ImageView)findViewById(R.id.imageView9);
        btSave.setOnClickListener(new onSave());
        edit_name = (EditText)findViewById(R.id.editText1);
        
        /////////////����////////////
        btCopy = (ImageView)findViewById(R.id.imageView3);
        btCopy.setOnClickListener(new onCopy());
        btInvert = (ImageView)findViewById(R.id.imageView5);
        btInvert.setOnClickListener(new onInvert());
        btFlip = (ImageView)findViewById(R.id.imageView11);
        btFlip.setOnClickListener(new onFlip());
        btMirror = (ImageView)findViewById(R.id.imageView6);
        btMirror.setOnClickListener(new onMirror());
        btRepeat = (ImageView)findViewById(R.id.imageView7);
        btRepeat.setOnClickListener(new onRepeat());
        btBlur = (ImageView)findViewById(R.id.imageView1);
        btBlur.setOnClickListener(new onBlur());
        btEdges = (ImageView)findViewById(R.id.imageView4);
        btEdges.setOnClickListener(new onEdges());
        btCartoon = (ImageView)findViewById(R.id.imageView2);
        btCartoon.setOnClickListener(new onCartoon()); 
        btSpace = (ImageView)findViewById(R.id.imageView8);
        btSpace.setOnClickListener(new onSpace());
        btBlackOut = (ImageView)findViewById(R.id.imageView13);
        btBlackOut.setOnClickListener(new onBlackOut());
	    
	}
	 void clickA(){
	    	click++;
	    	if(click==1){
				Bitmap frame1 = BitmapFactory.decodeResource(getResources(), R.drawable.pl6);
				input=frame1;
				iv.setImageBitmap(input);
			}
			if(click==2){
				Bitmap frame2 = BitmapFactory.decodeResource(getResources(), R.drawable.pl2);
				input=frame2;
				iv.setImageBitmap(input);
			}
			if(click==3){
				Bitmap frame3 = BitmapFactory.decodeResource(getResources(), R.drawable.pl3);
				input=frame3;
				iv.setImageBitmap(input);
			}
			if(click==4){
				Bitmap frame4 = BitmapFactory.decodeResource(getResources(), R.drawable.pl4);
				input=frame4;
				iv.setImageBitmap(input);
			}
			if(click==5){
				Bitmap frame5 = BitmapFactory.decodeResource(getResources(), R.drawable.pl5);
				input=frame5;
				iv.setImageBitmap(input);
			}
			if(click==6){
				Bitmap frame6 = BitmapFactory.decodeResource(getResources(), R.drawable.pl);
				input=frame6;
				iv.setImageBitmap(input);
				click=0;
			}
	    }
	// ������ ���Ͽ� �̹��� �ҷ�����
	public class onAdd implements OnClickListener {
		public void onClick(View v) {
			clickA();
			// ������ ȭ������ �Ѿ�� �κ�
			Toast.makeText(getApplicationContext(), 
					"�༺�� ������",
					Toast.LENGTH_SHORT).show();
		}
	}

	// ���ְ������� ����
	// �ؽ�Ʈ ���� �߰��� �����ϱ� - ����������.
		public class onSave implements OnClickListener {
			public void onClick(View v) {
				String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures/";
				String fileName = "TAB3_"+edit_name.getText().toString()+".jpg";
				path = path+fileName;			
					try {
						FileOutputStream fos = new FileOutputStream(path);
						output.compress(Bitmap.CompressFormat.JPEG, 100, fos);				
						fos.close();
						Toast.makeText(getApplicationContext(), "����Ϸ�", Toast.LENGTH_SHORT).show();				
						sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
					} catch (Exception e) {}		
				}
		}
//////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////
//////////////////////////////////////////////////////////////////////////////////////////////////////
		public class onBlackOut implements OnClickListener {
			public void onClick(View v) {
				int width, height;
				int[][] red;
				int[][] green;
				int[][] blue;
				int r, g, b;			
				
				width = input.getWidth();
				height = input.getHeight();
				output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
					
				red = new int[height][width];
				green = new int[height][width];
				blue = new int[height][width];
			
				
				// �Է� �̹����κ��� �ȼ��� ��������
				for(int h=0; h<height; h++) {
					for(int w=0; w<width; w++) {
						red[h][w] = Color.red(input.getPixel(w, h));
						green[h][w] = Color.green(input.getPixel(w, h));
						blue[h][w] = Color.blue(input.getPixel(w, h));		
					}
				}

				// ��� �̹����� �ȼ��� ä���ֱ�			
				for(int h=0; h<height; h++) {
					for(int w=0; w<width; w++) {
						if((red[h][w] == 0) && (green[h][w] == 0) && (blue[h][w] == 0)) {					
							r = (255 - red[h][w])+Color.TRANSPARENT; 
							g = (255 - green[h][w])+Color.TRANSPARENT;
							b = (255 - blue[h][w])+Color.TRANSPARENT;
							
							output.setPixel(w, h, Color.rgb(r, g, b));
						}
						else {
							r = red[h][w];
							g = green[h][w];
							b = blue[h][w];
							output.setPixel(w, h, Color.rgb(r, g, b));
						}	
					}
				}

				iv.setImageBitmap(output);
				input=output;
			}
		}
		public class onSpace implements OnClickListener {
			public void onClick(View v) {
				Bitmap frame = BitmapFactory.decodeResource(getResources(), R.drawable.pppp);
			
				
				int width1, height1;
				int width2, height2;		
				
				int[][] red1; 
				int[][] green1;
				int[][] blue1;
				
				int[][] red2; 
				int[][] green2;
				int[][] blue2; 
				
				int r, g, b;
				
			
				width1 = input.getWidth(); height1 = input.getHeight();
				width2 = frame.getWidth(); height2 = frame.getHeight();
				
				output = Bitmap.createBitmap(width1, height1, Bitmap.Config.ARGB_8888); 
				//input2 = Bitmap.createBitmap(width2, height2, Bitmap.Config.ARGB_8888); 
				
				red1 = new int[height1][width1]; 
				green1 = new int[height1][width1]; 
				blue1 = new int[height1][width1]; 
				
				red2 = new int[height2][width2]; 
				green2 = new int[height2][width2]; 
				blue2 = new int[height2][width2]; 
				
				// �Է� �̹����κ��� �ȼ��� ��������
				for(int h=0; h<height1; h++) {
					for(int w=0; w<width1; w++) {
						//�̹���1�� RGB1�� ����
						red1[h][w] = Color.red(input.getPixel(w, h));
						green1[h][w] = Color.green(input.getPixel(w, h));
						blue1[h][w] = Color.blue(input.getPixel(w, h));
					}
				}
				for(int h=0; h<height2; h++) {
					for(int w=0; w<width2; w++) {
						red2[h][w] = Color.red(frame.getPixel(w, h));
						green2[h][w] = Color.green(frame.getPixel(w, h));
						blue2[h][w] = Color.blue(frame.getPixel(w, h));
					}
				}
				
				// ��� �̹����� �ȼ��� ä���ֱ�		
				for(int h=0; h<height1; h++) {
					for(int w=0; w<width1; w++) {
						r = red1[h][w]+red2[h][w];
						g = green1[h][w]+green2[h][w];
						b = blue1[h][w]+blue2[h][w];
						if(r > 255) r = 255; if(r < 0) r = -r;
						if(g > 255) g = 255; if(g < 0) r = -g;
						if(b > 255) b = 255; if(b < 0) r = -b;
						
						output.setPixel(w, h, Color.rgb(r, g, b)); 
					}
				}	
				iv.setImageBitmap(output);
				input=output;
			}
		}

		public class onCopy implements OnClickListener {
			public void onClick(View v) {	
				
				int width, height;
				int[][] red; //�迭����
				int[][] green;
				int[][] blue;
				int r, g, b;			

				width = input.getWidth();
				height = input.getHeight();
				output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); 
				// input ũ�⸸ŭ output(empty image) ����

				red = new int[height][width]; //red �迭����
				green = new int[height][width];
				blue = new int[height][width];

				// �Է� �̹����κ��� �ȼ��� ��������
				for(int h=0; h<height; h++) {
					for(int w=0; w<width; w++) {
						red[h][w] = Color.red(input.getPixel(w, h));
						green[h][w] = Color.green(input.getPixel(w, h));
						blue[h][w] = Color.blue(input.getPixel(w, h));
					}
				}

				// ��� �̹����� �ȼ��� ä���ֱ�			
				for(int h=0; h<height; h++) {
					for(int w=0; w<width; w++) {
						r = red[h][w];
						g = green[h][w];
						b = blue[h][w];
						output.setPixel(w, h, Color.rgb(r, g, b));
					}
				}

				iv.setImageBitmap(output);	
				input=output;
			}
		}

		public class onInvert implements OnClickListener {
			public void onClick(View v) {			
				int width, height;			
				int[][] red;
				int[][] green;
				int[][] blue;
				int r, g, b;			

				width = input.getWidth();
				height = input.getHeight();
				output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); 

				red = new int[height][width];
				green = new int[height][width];
				blue = new int[height][width];

				// �Է� �̹����κ��� �ȼ��� ��������
				for(int h=0; h<height; h++) {
					for(int w=0; w<width; w++) {
						red[h][w] = Color.red(input.getPixel(w, h));
						green[h][w] = Color.green(input.getPixel(w, h));
						blue[h][w] = Color.blue(input.getPixel(w, h));
					}
				}

				// ��� �̹����� �ȼ��� ä���ֱ�
				for(int h=0; h<height; h++) {
					for(int w=0; w<width; w++) {
						// �� �ȼ����� ������ 0~255 
						r = 255 - red[h][w]; 
						g = 255 - green[h][w];
						b = 255 - blue[h][w];
						output.setPixel(w, h, Color.rgb(r, g, b));
					}
				}

				iv.setImageBitmap(output);
				input=output;
			}
		}

		public class onFlip implements OnClickListener {
			public void onClick(View v) {					
				int width, height;			
				int[][] red;
				int[][] green;
				int[][] blue;
				int r, g, b;

				width = input.getWidth();
				height = input.getHeight();
				output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); 

				red = new int[height][width];
				green = new int[height][width];
				blue = new int[height][width];

				// �Է� �̹����κ��� �ȼ��� ��������
				for(int h=0; h<height; h++) {
					for(int w=0; w<width; w++) {
						red[h][w] = Color.red(input.getPixel(w, h));
						green[h][w] = Color.green(input.getPixel(w, h));
						blue[h][w] = Color.blue(input.getPixel(w, h));
					}
				}

				// ��� �̹����� �ȼ��� ä���ֱ�		
				for(int h=0; h<height; h++) {
					for(int w=0; w<width; w++) {
						r = red[h][w];
						g = green[h][w];
						b = blue[h][w];					
						output.setPixel(width-1-w, h, Color.rgb(r, g, b)); // �����ʿ������� �������� ä��
					}
				}			

				iv.setImageBitmap(output);
				input=output;
			}
		}

		public class onMirror implements OnClickListener {
			public void onClick(View v) {				
				int width, height;			
				int[][] red;
				int[][] green;
				int[][] blue;
				int r, g, b;		

				width = input.getWidth();
				height = input.getHeight();
				// ���θ� �ι�� �� 
				output = Bitmap.createBitmap(width*2, height, Bitmap.Config.ARGB_8888); 

				red = new int[height][width];
				green = new int[height][width];
				blue = new int[height][width];

				// �Է� �̹����κ��� �ȼ��� ��������
				for(int h=0; h<height; h++) {
					for(int w=0; w<width; w++) {
						red[h][w] = Color.red(input.getPixel(w, h));
						green[h][w] = Color.green(input.getPixel(w, h));
						blue[h][w] = Color.blue(input.getPixel(w, h));
					}
				}

				// ��� �̹����� �ȼ��� ä���ֱ�			
				for(int h=0; h<height; h++) {
					for(int w=0; w<width; w++) {
						r = red[h][w];
						g = green[h][w];
						b = blue[h][w];
						output.setPixel(w, h, Color.rgb(r, g, b)); // ���� ������ ���ʿ��� ���������� ä���					
						output.setPixel((width*2)-1-w, h, Color.rgb(r, g, b)); // ������ ������ �����ʿ��� �������� ä��
					}
				}

				iv.setImageBitmap(output);	
				input=output;
			}
		}

		public class onRepeat implements OnClickListener {
			public void onClick(View v) {				
				int width, height;			
				int[][] red;
				int[][] green;
				int[][] blue;
				int r, g, b;		

				width = input.getWidth();
				height = input.getHeight();
				// ����, ���θ� �ι�� ��
				output = Bitmap.createBitmap(width*2, height*2, Bitmap.Config.ARGB_8888); 

				red = new int[height][width];
				green = new int[height][width];
				blue = new int[height][width];

				// �Է� �̹����κ��� �ȼ��� ��������
				for(int h=0; h<height; h++) {
					for(int w=0; w<width; w++) {
						red[h][w] = Color.red(input.getPixel(w, h));
						green[h][w] = Color.green(input.getPixel(w, h));
						blue[h][w] = Color.blue(input.getPixel(w, h));
					}
				}

				// ��� �̹����� �ȼ��� ä���ֱ�			
				for(int h=0; h<height; h++) {
					for(int w=0; w<width; w++) {
						r = red[h][w];
						g = green[h][w];
						b = blue[h][w];
						output.setPixel(w, h, Color.rgb(r, 0, 0)); // �»�
						output.setPixel(width+w, h, Color.rgb(0, g, 0)); // ���
						output.setPixel(w, height+h, Color.rgb(0, 0, b)); // ����
						output.setPixel(width+w, height+h, Color.rgb((r+g+b)/3, (r+g+b)/3, (r+g+b)/3)); // ����
					}
				}

				iv.setImageBitmap(output);	
				input=output;
			}
		}

		public class onBlur implements OnClickListener {
			public void onClick(View v) {					
				int width, height;			
				int[][] red;
				int[][] green;
				int[][] blue;
				int r, g, b;

				width = input.getWidth();
				height = input.getHeight();
				output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); 

				red = new int[height][width];
				green = new int[height][width];
				blue = new int[height][width];

				// �Է� �̹����κ��� �ȼ��� ��������
				for(int h=0; h<height; h++) {
					for(int w=0; w<width; w++) {
						red[h][w] = Color.red(input.getPixel(w, h));
						green[h][w] = Color.green(input.getPixel(w, h));
						blue[h][w] = Color.blue(input.getPixel(w, h));
					}
				}

				// ��� �̹����� �ȼ��� ä���ֱ�
				for(int h=1; h<height-1; h++) { // �̹����� �����ڸ��� ������
					for(int w=1; w<width-1; w++) {
						// �ڽ��� ������ �ֺ� �ȼ�(�� 9��)���� ��� ���
						r = red[h-1][w-1] + red[h-1][w] + red[h-1][w+1] +
								red[h  ][w-1] + red[h  ][w] + red[h  ][w+1] +
								red[h+1][w-1] + red[h+1][w] + red[h+1][w+1];
						r = r/9;

						g = green[h-1][w-1] + green[h-1][w] + green[h-1][w+1] +
								green[h  ][w-1] + green[h  ][w] + green[h  ][w+1] +
								green[h+1][w-1] + green[h+1][w] + green[h+1][w+1];
						g = g/9;

						b = blue[h-1][w-1] + blue[h-1][w] + blue[h-1][w+1] +
								blue[h  ][w-1] + blue[h  ][w] + blue[h  ][w+1] +
								blue[h+1][w-1] + blue[h+1][w] + blue[h+1][w+1];
						b = b/9;

						output.setPixel(w, h, Color.rgb(r, g, b));
					}
				}

				iv.setImageBitmap(output);
				input=output;
			}
		}

		public class onEdges implements OnClickListener {
			public void onClick(View v) {			
				int width, height;			
				int[][] red;
				int[][] green;
				int[][] blue;
				int r, g, b;

				width = input.getWidth();
				height = input.getHeight();
				output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); 

				red = new int[height][width];
				green = new int[height][width];
				blue = new int[height][width];

				// �Է� �̹����κ��� �ȼ��� ��������
				for(int h=0; h<height; h++) {
					for(int w=0; w<width; w++) {
						red[h][w] = Color.red(input.getPixel(w, h));
						green[h][w] = Color.green(input.getPixel(w, h));
						blue[h][w] = Color.blue(input.getPixel(w, h));
					}
				}

				// ��� �̹����� �ȼ��� ä���ֱ�
				for(int h=1; h<height-1; h++) { // �̹����� �����ڸ��� ������
					for(int w=1; w<width-1; w++) {					
						r = red[h-1][w-1]*1 + red[h-1][w]*0 + red[h-1][w+1]*-1 +
								red[h  ][w-1]*2 + red[h  ][w]*0 + red[h  ][w+1]*-2 +
								red[h+1][w-1]*1 + red[h+1][w]*0 + red[h+1][w+1]*-1;					
						if(r < 0) r = -r; // ������ ������ ����� �ٲ��ְ�,
						if(r > 255) r = 255; // ���� ũ�Ⱑ 255�� ���� �ʰ� ��

						output.setPixel(w, h, Color.rgb(r, r, r));
					}
				}			

				iv.setImageBitmap(output);
				input=output;
			}
		}

		public class onCartoon implements OnClickListener {
			public void onClick(View v) {					
				int width, height;			
				int[][] red;
				int[][] green;
				int[][] blue;
				int r, g, b;
				int level = 25;
				int sum;

				width = input.getWidth();
				height = input.getHeight();
				output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); 

				red = new int[height][width];
				green = new int[height][width];
				blue = new int[height][width];

				// �Է� �̹����κ��� �ȼ��� ��������
				for(int h=0; h<height; h++) {
					for(int w=0; w<width; w++) {
						red[h][w] = Color.red(input.getPixel(w, h));
						green[h][w] = Color.green(input.getPixel(w, h));
						blue[h][w] = Color.blue(input.getPixel(w, h));
					}
				}

				// ��� �̹����� �ȼ��� ä���ֱ�
				for(int h=1; h<height-1; h++) { // �̹����� �����ڸ��� ������
					for(int w=1; w<width-1; w++) {
						r = red[h-1][w-1]*1 + red[h-1][w]*0 + red[h-1][w+1]*-1 +
								red[h  ][w-1]*2 + red[h  ][w]*0 + red[h  ][w+1]*-2 +
								red[h+1][w-1]*1 + red[h+1][w]*0 + red[h+1][w+1]*-1;					
						g = green[h-1][w-1]*1 + green[h-1][w]*0 + green[h-1][w+1]*-1 +
								green[h  ][w-1]*2 + green[h  ][w]*0 + green[h  ][w+1]*-2 +
								green[h+1][w-1]*1 + green[h+1][w]*0 + green[h+1][w+1]*-1;					
						b = blue[h-1][w-1]*1 + blue[h-1][w]*0 + blue[h-1][w+1]*-1 +
								blue[h  ][w-1]*2 + blue[h  ][w]*0 + blue[h  ][w+1]*-2 +
								blue[h+1][w-1]*1 + blue[h+1][w]*0 + blue[h+1][w+1]*-1;					

						if(r < 0) r = -r;	if(r > 255) r = 255;
						if(g < 0) g = -g;	if(g > 255) g = 255;
						if(b < 0) b = -b;	if(b > 255) b = 255;					
						sum = (r+g+b)/3;							

						if(sum > 100) { // �����κ��� ���������� ĥ��
							output.setPixel(w, h, Color.rgb(0, 0, 0));
						} 
						else { // ������ �ƴ� �κ��� ���� �ܼ�ȭ ���Ѽ� ĥ��						
							red[h][w] = red[h][w]/level * level;
							green[h][w] = green[h][w]/level * level;
							blue[h][w] = blue[h][w]/level * level;					
							output.setPixel(w, h, Color.rgb(red[h][w], green[h][w], blue[h][w]));
						}
					}
				}			

				iv.setImageBitmap(output);
				input=output;
			}
		}
	}


	