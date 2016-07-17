package com.example.cosmoscam;

import java.io.File;
import java.io.FileOutputStream;

import com.example.cosmoscam.TabTwoA.onAdd;

import com.example.cosmoscam.TabTwoA.onBlur;
import com.example.cosmoscam.TabTwoA.onCartoon;
import com.example.cosmoscam.TabTwoA.onCopy;
import com.example.cosmoscam.TabTwoA.onEdges;
import com.example.cosmoscam.TabTwoA.onFlip;
import com.example.cosmoscam.TabTwoA.onInvert;
import com.example.cosmoscam.TabTwoA.onMirror;
import com.example.cosmoscam.TabTwoA.onOpen;
import com.example.cosmoscam.TabTwoA.onOriginal;
import com.example.cosmoscam.TabTwoA.onRepeat;
import com.example.cosmoscam.TabTwoA.onResult;
import com.example.cosmoscam.TabTwoA.onSave;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class TabTwoB extends Activity {
	ImageView btOpen, btCam, btResult, btSave, btEffect;
	ImageView iv;
	Bitmap input, output, input1, input2, output2;
	EditText edit_name;
	ImageView btCopy, btInvert, btFlip, btMirror, 
	btRepeat, btBlur, btEdges, btCartoon, btSpace;	
	ImageView btEarth;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.tab2b);
	    // TODO Auto-generated method stub
	    
	    iv = (ImageView)findViewById(R.id.imageView5);	
        btOpen = (ImageView)findViewById(R.id.imageView1);
        btOpen.setOnClickListener(new onOpen());
        btCam = (ImageView)findViewById(R.id.imageView2);
        btCam.setOnClickListener(new onCam());
        btResult = (ImageView)findViewById(R.id.imageView3);
        btResult.setOnClickListener(new onResult());
        btSave = (ImageView)findViewById(R.id.imageView4);
        btSave.setOnClickListener(new onSave());
        edit_name = (EditText)findViewById(R.id.editText1);
        /////////////����////////////
        btCopy = (ImageView)findViewById(R.id.imageView6);
        btCopy.setOnClickListener(new onCopy());
        btInvert = (ImageView)findViewById(R.id.imageView11);
        btInvert.setOnClickListener(new onInvert());
        btFlip = (ImageView)findViewById(R.id.imageView7);
        btFlip.setOnClickListener(new onFlip());
        btMirror = (ImageView)findViewById(R.id.imageView12);
        btMirror.setOnClickListener(new onMirror());
        btRepeat = (ImageView)findViewById(R.id.imageView13);
        btRepeat.setOnClickListener(new onRepeat());
        btBlur = (ImageView)findViewById(R.id.imageView8);
        btBlur.setOnClickListener(new onBlur());
        btEdges = (ImageView)findViewById(R.id.imageView10);
        btEdges.setOnClickListener(new onEdges());
        btCartoon = (ImageView)findViewById(R.id.imageView9);
        btCartoon.setOnClickListener(new onCartoon()); 
        
        btSpace = (ImageView)findViewById(R.id.imageView14);
        btSpace.setOnClickListener(new onSpace());
        btEarth = (ImageView)findViewById(R.id.imageView15);
        btEarth.setOnClickListener(new onEarth());
	}
	// ���������� �̹��� ��������
	public class onOpen implements OnClickListener {
		public void onClick(View v) {
			// ������ ȭ������ �Ѿ�� �κ�
			Intent intent = new Intent(Intent.ACTION_PICK, 
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(intent, 1);
		}
	}
	// �������
	public class onCam implements OnClickListener {
		public void onClick(View v) {
			Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
    		File file = new File(Environment.getExternalStorageDirectory(), "camera.jpg");
    		intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(file));
    		startActivityForResult(intent, 2); // ī�޶� ������ �̵���(requestCode�� 0���� ����)
		}
	}
	// ������ ȭ�� ������ ����Ǵ� �κ� 1
	public void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);
		
		

		// ���������� ������ �̹����� input�� �����Ѵ�
		if(requestCode==1){ // ���������� ������ �̹��� ��θ� �˾Ƴ���
			String[] str = {MediaStore.Images.Media.DATA};
			Cursor c = getContentResolver().query(data.getData(), str, null, null, null);
			c.moveToFirst();
			String path = c.getString(c.getColumnIndex(str[0]));
			c.close();

			// �̹����� �ʹ� ũ�� �ٿ����� (�޸� ����)
			BitmapFactory.Options opts = new BitmapFactory.Options();
			opts.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(path, opts);

			int MAX_SIZE = 500;
			int sampleSize = 1;		
			if((opts.outWidth > MAX_SIZE) || (opts.outHeight > MAX_SIZE)) {
				int widthRatio = (int)((float)opts.outWidth / MAX_SIZE);
				int heightRatio = (int)((float)opts.outHeight / MAX_SIZE);			
				if(widthRatio > heightRatio) sampleSize = heightRatio;
				else sampleSize = widthRatio;
			}
			opts.inSampleSize = sampleSize;
			opts.inJustDecodeBounds = false;		
			input = BitmapFactory.decodeFile(path, opts);
			iv.setImageBitmap(input);
			Toast.makeText(getApplicationContext(), 
					"W:"+input.getWidth()+", H:"+input.getHeight(),
					Toast.LENGTH_SHORT).show();
		}

		if(requestCode==2) {
			// ī�޶�� ���� �̹��� ��θ� �˾Ƴ���
			String path1 = Environment.getExternalStorageDirectory()+"/camera.jpg";

			// �̹����� �ʹ� ũ�� �ٿ����� (�޸� ����)
			BitmapFactory.Options opts1 = new BitmapFactory.Options();
			opts1.inJustDecodeBounds = true;
			BitmapFactory.decodeFile(path1, opts1);
			int MAX_SIZE1 = 500;
			int sampleSize1 = 1;
			if((opts1.outWidth > MAX_SIZE1) || (opts1.outHeight > MAX_SIZE1)) {
				int widthRatio = (int)((float)opts1.outWidth / MAX_SIZE1);
				int heightRatio = (int)((float)opts1.outHeight / MAX_SIZE1);   
				if(widthRatio > heightRatio) sampleSize1 = heightRatio;
				else sampleSize1 = widthRatio;
			}
			opts1.inSampleSize = sampleSize1;
			opts1.inJustDecodeBounds = false;

			// �̹����� input �� ������
			input = BitmapFactory.decodeFile(path1, opts1); 
			iv.setImageBitmap(input);
		}
	}
	// ������� ������ ���ֹ�� ��ġ��
	public class onResult implements OnClickListener {
		public void onClick(View v) {
			Bitmap frame = BitmapFactory.decodeResource(getResources(), R.drawable.suit4);
			Bitmap picture = Bitmap.createScaledBitmap(input, frame.getWidth(), frame.getHeight(), true); 

			int width, height;
			int r, g, b;
			

			width = frame.getWidth();
			height = frame.getHeight();
			output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

			

			// ��� �̹����� �ȼ��� ä���ֱ�			
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					if(Color.alpha(frame.getPixel(w, h))==0){
						r= Color.red(picture.getPixel(w, h));
						g= Color.green(picture.getPixel(w, h));
						b= Color.blue(picture.getPixel(w, h));
					}
					else{
						r= Color.red(frame.getPixel(w, h));
						g= Color.green(frame.getPixel(w, h));
						b= Color.blue(frame.getPixel(w, h));
					}					
					output.setPixel(w, h, Color.rgb(r, g, b));
				}
			}

			iv.setImageBitmap(output);

		}
	}
	// ���ְ������� ����
	public class onSave implements OnClickListener {
		public void onClick(View v) {
			String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures/";
			String fileName = "TAB2_b_"+edit_name.getText().toString()+".jpg";
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
	public class onEarth implements OnClickListener {
		public void onClick(View v) {
			Bitmap frame = BitmapFactory.decodeResource(getResources(), R.drawable.suitandearth);
			Bitmap picture = Bitmap.createScaledBitmap(output, frame.getWidth(), frame.getHeight(), true); 

			int width, height;
			int r, g, b;
			

			width = frame.getWidth();
			height = frame.getHeight();
			output2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

			

			// ��� �̹����� �ȼ��� ä���ֱ�			
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					r =  Color.red(picture.getPixel(w, h))+Color.red(frame.getPixel(w, h));
					g = Color.green(picture.getPixel(w, h))+Color.green(frame.getPixel(w, h));
					b = Color.blue(picture.getPixel(w, h))+Color.blue(frame.getPixel(w, h));
					if(r > 255) r = 255; if(r < 0) r = -r;
					if(g > 255) g = 255; if(g < 0) r = -g;
					if(b > 255) b = 255; if(b < 0) r = -b;
					
					output2.setPixel(w, h, Color.rgb(r, g, b));
				}
			}

			iv.setImageBitmap(output2);
			output=output2;
		}
	}
	public class onSpace implements OnClickListener {
		public void onClick(View v) {
			Bitmap frame = BitmapFactory.decodeResource(getResources(), R.drawable.ppp);
			Bitmap picture = Bitmap.createScaledBitmap(output, frame.getWidth(), frame.getHeight(), true); 

			int width, height;
			int r, g, b;
			

			width = frame.getWidth();
			height = frame.getHeight();
			output2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

			

			// ��� �̹����� �ȼ��� ä���ֱ�			
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					r =  Color.red(picture.getPixel(w, h))+Color.red(frame.getPixel(w, h));
					g = Color.green(picture.getPixel(w, h))+Color.green(frame.getPixel(w, h));
					b = Color.blue(picture.getPixel(w, h))+Color.blue(frame.getPixel(w, h));
					if(r > 255) r = 255; if(r < 0) r = -r;
					if(g > 255) g = 255; if(g < 0) r = -g;
					if(b > 255) b = 255; if(b < 0) r = -b;
					
					output2.setPixel(w, h, Color.rgb(r, g, b));
				}
			}

			iv.setImageBitmap(output2);
			output=output2;
		}
	}
	
	public class onCopy implements OnClickListener {
		public void onClick(View v) {		
			int width, height;
			int[][] red; //�迭����
			int[][] green;
			int[][] blue;
			int r, g, b;			

			width = output.getWidth();
			height = output.getHeight();
			output2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); 
			// input ũ�⸸ŭ output(empty image) ����

			red = new int[height][width]; //red �迭����
			green = new int[height][width];
			blue = new int[height][width];

			// �Է� �̹����κ��� �ȼ��� ��������
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					red[h][w] = Color.red(output.getPixel(w, h));
					green[h][w] = Color.green(output.getPixel(w, h));
					blue[h][w] = Color.blue(output.getPixel(w, h));
				}
			}

			// ��� �̹����� �ȼ��� ä���ֱ�			
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					r = red[h][w];
					g = green[h][w];
					b = blue[h][w];
					output2.setPixel(w, h, Color.rgb(r, g, b));
				}
			}

			iv.setImageBitmap(output2);	
			output = output2;
		}
	}

	public class onInvert implements OnClickListener {
		public void onClick(View v) {			
			int width, height;			
			int[][] red;
			int[][] green;
			int[][] blue;
			int r, g, b;			

			width = output.getWidth();
			height = output.getHeight();
			output2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); 

			red = new int[height][width];
			green = new int[height][width];
			blue = new int[height][width];

			// �Է� �̹����κ��� �ȼ��� ��������
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					red[h][w] = Color.red(output.getPixel(w, h));
					green[h][w] = Color.green(output.getPixel(w, h));
					blue[h][w] = Color.blue(output.getPixel(w, h));
				}
			}

			// ��� �̹����� �ȼ��� ä���ֱ�
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					// �� �ȼ����� ������ 0~255 
					r = 255 - red[h][w]; 
					g = 255 - green[h][w];
					b = 255 - blue[h][w];
					output2.setPixel(w, h, Color.rgb(r, g, b));
				}
			}

			iv.setImageBitmap(output2);
			output = output2;
		}
	}

	public class onFlip implements OnClickListener {
		public void onClick(View v) {					
			int width, height;			
			int[][] red;
			int[][] green;
			int[][] blue;
			int r, g, b;

			width = output.getWidth();
			height = output.getHeight();
			output2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); 

			red = new int[height][width];
			green = new int[height][width];
			blue = new int[height][width];

			// �Է� �̹����κ��� �ȼ��� ��������
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					red[h][w] = Color.red(output.getPixel(w, h));
					green[h][w] = Color.green(output.getPixel(w, h));
					blue[h][w] = Color.blue(output.getPixel(w, h));
				}
			}

			// ��� �̹����� �ȼ��� ä���ֱ�		
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					r = red[h][w];
					g = green[h][w];
					b = blue[h][w];					
					output2.setPixel(width-1-w, h, Color.rgb(r, g, b)); // �����ʿ������� �������� ä��
				}
			}			

			iv.setImageBitmap(output2);
			output = output2;
		}
	}

	public class onMirror implements OnClickListener {
		public void onClick(View v) {				
			int width, height;			
			int[][] red;
			int[][] green;
			int[][] blue;
			int r, g, b;		

			width = output.getWidth();
			height = output.getHeight();
			// ���θ� �ι�� �� 
			output2 = Bitmap.createBitmap(width*2, height, Bitmap.Config.ARGB_8888); 

			red = new int[height][width];
			green = new int[height][width];
			blue = new int[height][width];

			// �Է� �̹����κ��� �ȼ��� ��������
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					red[h][w] = Color.red(output.getPixel(w, h));
					green[h][w] = Color.green(output.getPixel(w, h));
					blue[h][w] = Color.blue(output.getPixel(w, h));
				}
			}

			// ��� �̹����� �ȼ��� ä���ֱ�			
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					r = red[h][w];
					g = green[h][w];
					b = blue[h][w];
					output2.setPixel(w, h, Color.rgb(r, g, b)); // ���� ������ ���ʿ��� ���������� ä���					
					output2.setPixel((width*2)-1-w, h, Color.rgb(r, g, b)); // ������ ������ �����ʿ��� �������� ä��
				}
			}

			iv.setImageBitmap(output2);	

			output = output2;
		}
	}

	public class onRepeat implements OnClickListener {
		public void onClick(View v) {				
			int width, height;			
			int[][] red;
			int[][] green;
			int[][] blue;
			int r, g, b;		

			width = output.getWidth();
			height = output.getHeight();
			// ����, ���θ� �ι�� ��
			output2 = Bitmap.createBitmap(width*2, height*2, Bitmap.Config.ARGB_8888); 

			red = new int[height][width];
			green = new int[height][width];
			blue = new int[height][width];

			// �Է� �̹����κ��� �ȼ��� ��������
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					red[h][w] = Color.red(output.getPixel(w, h));
					green[h][w] = Color.green(output.getPixel(w, h));
					blue[h][w] = Color.blue(output.getPixel(w, h));
				}
			}

			// ��� �̹����� �ȼ��� ä���ֱ�			
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					r = red[h][w];
					g = green[h][w];
					b = blue[h][w];
					output2.setPixel(w, h, Color.rgb(r, 0, 0)); // �»�
					output2.setPixel(width+w, h, Color.rgb(0, g, 0)); // ���
					output2.setPixel(w, height+h, Color.rgb(0, 0, b)); // ����
					output2.setPixel(width+w, height+h, Color.rgb((r+g+b)/3, (r+g+b)/3, (r+g+b)/3)); // ����
				}
			}

			iv.setImageBitmap(output2);	
			output = output2;
		}
	}

	public class onBlur implements OnClickListener {
		public void onClick(View v) {					
			int width, height;			
			int[][] red;
			int[][] green;
			int[][] blue;
			int r, g, b;

			width = output.getWidth();
			height = output.getHeight();
			output2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); 

			red = new int[height][width];
			green = new int[height][width];
			blue = new int[height][width];

			// �Է� �̹����κ��� �ȼ��� ��������
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					red[h][w] = Color.red(output.getPixel(w, h));
					green[h][w] = Color.green(output.getPixel(w, h));
					blue[h][w] = Color.blue(output.getPixel(w, h));
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

					output2.setPixel(w, h, Color.rgb(r, g, b));
				}
			}

			iv.setImageBitmap(output2);
			output = output2;
		}
	}

	public class onEdges implements OnClickListener {
		public void onClick(View v) {			
			int width, height;			
			int[][] red;
			int[][] green;
			int[][] blue;
			int r, g, b;

			width = output.getWidth();
			height = output.getHeight();
			output2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); 

			red = new int[height][width];
			green = new int[height][width];
			blue = new int[height][width];

			// �Է� �̹����κ��� �ȼ��� ��������
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					red[h][w] = Color.red(output.getPixel(w, h));
					green[h][w] = Color.green(output.getPixel(w, h));
					blue[h][w] = Color.blue(output.getPixel(w, h));
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

					output2.setPixel(w, h, Color.rgb(r, r, r));
				}
			}			

			iv.setImageBitmap(output2);
			output = output2;
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

			width = output.getWidth();
			height = output.getHeight();
			output2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); 

			red = new int[height][width];
			green = new int[height][width];
			blue = new int[height][width];

			// �Է� �̹����κ��� �ȼ��� ��������
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					red[h][w] = Color.red(output.getPixel(w, h));
					green[h][w] = Color.green(output.getPixel(w, h));
					blue[h][w] = Color.blue(output.getPixel(w, h));
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
						output2.setPixel(w, h, Color.rgb(0, 0, 0));
					} 
					else { // ������ �ƴ� �κ��� ���� �ܼ�ȭ ���Ѽ� ĥ��						
						red[h][w] = red[h][w]/level * level;
						green[h][w] = green[h][w]/level * level;
						blue[h][w] = blue[h][w]/level * level;					
						output2.setPixel(w, h, Color.rgb(red[h][w], green[h][w], blue[h][w]));
					}
				}
			}			

			iv.setImageBitmap(output2);
			output = output2;
		}
	}

}
