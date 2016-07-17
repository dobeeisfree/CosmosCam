package com.example.cosmoscam;



import java.io.FileOutputStream;
import java.util.Random;
import android.util.Log;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.RadialGradient;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.widget.Button;
import android.database.Cursor;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class TabTwoA extends Activity {
	ImageView btOpen, btAdd, btResult, btSave, btEffect;
	ImageView iv;
	Bitmap input, output, input1, input2, output2;
	EditText edit_name;
	ImageView btCopy, btInvert, btFlip, btMirror, 
				btRepeat, btBlur, btEdges, btCartoon, btOriginal;
	ImageView btAlien;

	int click=0;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setContentView(R.layout.tab2a);
	    // TODO Auto-generated method stub
	    
	    iv = (ImageView)findViewById(R.id.imageView5);	
        btOpen = (ImageView)findViewById(R.id.imageView2);
        btOpen.setOnClickListener(new onOpen());
        btAdd = (ImageView)findViewById(R.id.imageView1);
        btAdd.setOnClickListener(new onAdd());
        btResult = (ImageView)findViewById(R.id.imageView3);
        btResult.setOnClickListener(new onResult());
        btSave = (ImageView)findViewById(R.id.imageView4);
        btSave.setOnClickListener(new onSave());
        btSave.setOnLongClickListener(new onSaaa());
        
        edit_name = (EditText)findViewById(R.id.editText1);
        /////////////필터////////////
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
        
        btOriginal = (ImageView)findViewById(R.id.imageView14);
        btOriginal.setOnClickListener(new onOriginal()); 
        ///////////////////////////////////////////////////////
        btAlien = (ImageView)findViewById(R.id.imageView15);
        btAlien.setOnClickListener(new onAlien()); 
	}
	

	public class onSaaa implements OnLongClickListener {
		@Override
		public boolean onLongClick(View v) {
			// TODO Auto-generated method stub
			return false;
		}
	}
	// 갤러리에서 이미지 가져오기
	public class onOpen implements OnClickListener {
		public void onClick(View v) {
			// 갤러리 화면으로 넘어가는 부분
			Intent intent = new Intent(Intent.ACTION_PICK, 
					android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			startActivityForResult(intent, 1);
		}
	}
	// 갤러리 화면 종료후 실행되는 부분
    public void onActivityResult(int requestCode, int resultCode, Intent data){
		super.onActivityResult(requestCode, resultCode, data);

		// 갤러리에서 선택한 이미지 경로를 알아낸다
		String[] str = {MediaStore.Images.Media.DATA};
		Cursor c = getContentResolver().query(data.getData(), str, null, null, null);
		c.moveToFirst();
		String path = c.getString(c.getColumnIndex(str[0]));
		c.close();
		
		// 이미지가 너무 크면 줄여주자 (메모리 문제)
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
		
		// 갤러리에서 선택한 이미지를 input에 복사한다
		if(requestCode==1){ 
			input1 = BitmapFactory.decodeFile(path, opts);
			iv.setImageBitmap(input1);
			Toast.makeText(getApplicationContext(), 
					"W:"+input1.getWidth()+", H:"+input1.getHeight(),
					Toast.LENGTH_SHORT).show();
		}
		else if(requestCode==2){
			input2 = BitmapFactory.decodeFile(path, opts);
			iv.setImageBitmap(input2);
			Toast.makeText(getApplicationContext(), 
					"W2:"+input2.getWidth()+", H2:"+input2.getHeight(),
					Toast.LENGTH_SHORT).show();
		}
	}
    
    public class onSave implements OnClickListener {
		public void onClick(View v) {
			String path = Environment.getExternalStorageDirectory().getAbsolutePath()+"/Pictures/";
			String fileName = "TAB2_a_"+edit_name.getText().toString()+".jpg";
			path = path+fileName;			
			try {
				FileOutputStream fos = new FileOutputStream(path);
				output.compress(Bitmap.CompressFormat.JPEG, 100, fos);				
				fos.close();
				Toast.makeText(getApplicationContext(), "저장완료", Toast.LENGTH_SHORT).show();				
				sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE, Uri.parse("file://" + path)));
			} catch (Exception e) {}			
		}
    }
    void clickA(){
    	click++;
    	if(click==1){
			Bitmap frame1 = BitmapFactory.decodeResource(getResources(), R.drawable.p);
			input2=frame1;
			iv.setImageBitmap(input2);
		}
		if(click==2){
			Bitmap frame2 = BitmapFactory.decodeResource(getResources(), R.drawable.pp);
			input2=frame2;
			iv.setImageBitmap(input2);
		}
		if(click==3){
			Bitmap frame3 = BitmapFactory.decodeResource(getResources(), R.drawable.bab2);
			input2=frame3;
			iv.setImageBitmap(input2);
		}
		if(click==4){
			Bitmap frame4 = BitmapFactory.decodeResource(getResources(), R.drawable.pp2);
			input2=frame4;
			iv.setImageBitmap(input2);
			click=0;
		}
    }
	// 우주배경 가져오기 
	public class onAdd implements OnClickListener {
		public void onClick(View v) {
			clickA();
			// 갤러리 화면으로 넘어가는 부분
			Toast.makeText(getApplicationContext(), 
					"우주사진이 선택됨",
					Toast.LENGTH_SHORT).show();
			
			//Intent intent = new Intent(Intent.ACTION_PICK, 
				//	android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
			//startActivityForResult(intent, 2);
			
		}
	}
	// 사용자의 사진과 우주배경 합치기
	public class onResult implements OnClickListener {
		public void onClick(View v) {
						
			int width1, height1;
			int width2, height2;		
			
			int[][] red1; 
			int[][] green1;
			int[][] blue1;
			
			int[][] red2; 
			int[][] green2;
			int[][] blue2; 
			
			int r, g, b;
			
		
			width1 = input1.getWidth(); height1 = input1.getHeight();
			width2 = input2.getWidth(); height2 = input2.getHeight();
			
			output = Bitmap.createBitmap(width1, height1, Bitmap.Config.ARGB_8888); 
			//input2 = Bitmap.createBitmap(width2, height2, Bitmap.Config.ARGB_8888); 
			
			red1 = new int[height1][width1]; 
			green1 = new int[height1][width1]; 
			blue1 = new int[height1][width1]; 
			
			red2 = new int[height2][width2]; 
			green2 = new int[height2][width2]; 
			blue2 = new int[height2][width2]; 
			
			// 입력 이미지로부터 픽셀값 가져오기
			for(int h=0; h<height1; h++) {
				for(int w=0; w<width1; w++) {
					//이미지1를 RGB1에 저장
					red1[h][w] = Color.red(input1.getPixel(w, h));
					green1[h][w] = Color.green(input1.getPixel(w, h));
					blue1[h][w] = Color.blue(input1.getPixel(w, h));
				}
			}
			for(int h=0; h<height2; h++) {
				for(int w=0; w<width2; w++) {
					red2[h][w] = Color.red(input2.getPixel(w, h));
					green2[h][w] = Color.green(input2.getPixel(w, h));
					blue2[h][w] = Color.blue(input2.getPixel(w, h));
				}
			}
			
			// 출력 이미지에 픽셀값 채워넣기		
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
			
		}
	}
	

	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	//////////////////////////////////////////////////////////////////////////////////////////////////////
	public class onAlien implements OnClickListener {
		public void onClick(View v) {
			Bitmap frame = BitmapFactory.decodeResource(getResources(), R.drawable.dhl1);
			Bitmap picture = Bitmap.createScaledBitmap(output, frame.getWidth(), frame.getHeight(), true); 

			int width, height;
			int r, g, b;
			

			width = frame.getWidth();
			height = frame.getHeight();
			output2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);

			

			// 출력 이미지에 픽셀값 채워넣기			
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
					output2.setPixel(w, h, Color.rgb(r, g, b));
				}
			}

			iv.setImageBitmap(output2);
			output = output2;
		}
	}
	
	public class onOriginal implements OnClickListener {
		public void onClick(View v) {
			
			int width1, height1;
			int width2, height2;		
			
			int[][] red1; 
			int[][] green1;
			int[][] blue1;
			
			int[][] red2; 
			int[][] green2;
			int[][] blue2; 
			
			int r, g, b;
			
		
			width1 = input1.getWidth(); height1 = input1.getHeight();
			width2 = input2.getWidth(); height2 = input2.getHeight();
			
			output = Bitmap.createBitmap(width1, height1, Bitmap.Config.ARGB_8888); 
			//input2 = Bitmap.createBitmap(width2, height2, Bitmap.Config.ARGB_8888); 
			
			red1 = new int[height1][width1]; 
			green1 = new int[height1][width1]; 
			blue1 = new int[height1][width1]; 
			
			red2 = new int[height2][width2]; 
			green2 = new int[height2][width2]; 
			blue2 = new int[height2][width2]; 
			
			// 입력 이미지로부터 픽셀값 가져오기
			for(int h=0; h<height1; h++) {
				for(int w=0; w<width1; w++) {
					//이미지1를 RGB1에 저장
					red1[h][w] = Color.red(input1.getPixel(w, h));
					green1[h][w] = Color.green(input1.getPixel(w, h));
					blue1[h][w] = Color.blue(input1.getPixel(w, h));
				}
			}
			for(int h=0; h<height2; h++) {
				for(int w=0; w<width2; w++) {
					red2[h][w] = Color.red(input2.getPixel(w, h));
					green2[h][w] = Color.green(input2.getPixel(w, h));
					blue2[h][w] = Color.blue(input2.getPixel(w, h));
				}
			}
			
			// 출력 이미지에 픽셀값 채워넣기		
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
		}
	}
	public class onCopy implements OnClickListener {
		public void onClick(View v) {		
			int width, height;
			int[][] red; //배열선언
			int[][] green;
			int[][] blue;
			int r, g, b;			
			
			width = output.getWidth();
			height = output.getHeight();
			output2 = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888); 
			// input 크기만큼 output(empty image) 생성
			
			red = new int[height][width]; //red 배열생성
			green = new int[height][width];
			blue = new int[height][width];
			
			// 입력 이미지로부터 픽셀값 가져오기
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					red[h][w] = Color.red(output.getPixel(w, h));
					green[h][w] = Color.green(output.getPixel(w, h));
					blue[h][w] = Color.blue(output.getPixel(w, h));
				}
			}
			
			// 출력 이미지에 픽셀값 채워넣기			
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
			
			// 입력 이미지로부터 픽셀값 가져오기
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					red[h][w] = Color.red(output.getPixel(w, h));
					green[h][w] = Color.green(output.getPixel(w, h));
					blue[h][w] = Color.blue(output.getPixel(w, h));
				}
			}
						
			// 출력 이미지에 픽셀값 채워넣기
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					// 각 픽셀값의 범위는 0~255 
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
			
			// 입력 이미지로부터 픽셀값 가져오기
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					red[h][w] = Color.red(output.getPixel(w, h));
					green[h][w] = Color.green(output.getPixel(w, h));
					blue[h][w] = Color.blue(output.getPixel(w, h));
				}
			}
						
			// 출력 이미지에 픽셀값 채워넣기		
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					r = red[h][w];
					g = green[h][w];
					b = blue[h][w];					
					output2.setPixel(width-1-w, h, Color.rgb(r, g, b)); // 오른쪽에서부터 왼쪽으로 채움
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
			// 가로를 두배로 함 
			output2 = Bitmap.createBitmap(width*2, height, Bitmap.Config.ARGB_8888); 
			
			red = new int[height][width];
			green = new int[height][width];
			blue = new int[height][width];
			
			// 입력 이미지로부터 픽셀값 가져오기
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					red[h][w] = Color.red(output.getPixel(w, h));
					green[h][w] = Color.green(output.getPixel(w, h));
					blue[h][w] = Color.blue(output.getPixel(w, h));
				}
			}
						
			// 출력 이미지에 픽셀값 채워넣기			
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					r = red[h][w];
					g = green[h][w];
					b = blue[h][w];
					output2.setPixel(w, h, Color.rgb(r, g, b)); // 왼쪽 절반은 왼쪽에서 오른쪽으로 채우고					
					output2.setPixel((width*2)-1-w, h, Color.rgb(r, g, b)); // 오른쪽 절반은 오른쪽에서 왼쪽으로 채움
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
			// 가로, 세로를 두배로 함
			output2 = Bitmap.createBitmap(width*2, height*2, Bitmap.Config.ARGB_8888); 
			
			red = new int[height][width];
			green = new int[height][width];
			blue = new int[height][width];
			
			// 입력 이미지로부터 픽셀값 가져오기
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					red[h][w] = Color.red(output.getPixel(w, h));
					green[h][w] = Color.green(output.getPixel(w, h));
					blue[h][w] = Color.blue(output.getPixel(w, h));
				}
			}
						
			// 출력 이미지에 픽셀값 채워넣기			
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					r = red[h][w];
					g = green[h][w];
					b = blue[h][w];
					output2.setPixel(w, h, Color.rgb(r, 0, 0)); // 좌상
					output2.setPixel(width+w, h, Color.rgb(0, g, 0)); // 우상
					output2.setPixel(w, height+h, Color.rgb(0, 0, b)); // 좌하
					output2.setPixel(width+w, height+h, Color.rgb((r+g+b)/3, (r+g+b)/3, (r+g+b)/3)); // 우하
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
			
			// 입력 이미지로부터 픽셀값 가져오기
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					red[h][w] = Color.red(output.getPixel(w, h));
					green[h][w] = Color.green(output.getPixel(w, h));
					blue[h][w] = Color.blue(output.getPixel(w, h));
				}
			}
						
			// 출력 이미지에 픽셀값 채워넣기
			for(int h=1; h<height-1; h++) { // 이미지의 가장자리는 제외함
				for(int w=1; w<width-1; w++) {
					// 자신을 포함한 주변 픽셀(총 9개)값의 평균 계산
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
			
			// 입력 이미지로부터 픽셀값 가져오기
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					red[h][w] = Color.red(output.getPixel(w, h));
					green[h][w] = Color.green(output.getPixel(w, h));
					blue[h][w] = Color.blue(output.getPixel(w, h));
				}
			}
						
			// 출력 이미지에 픽셀값 채워넣기
			for(int h=1; h<height-1; h++) { // 이미지의 가장자리는 제외함
				for(int w=1; w<width-1; w++) {					
					r = red[h-1][w-1]*1 + red[h-1][w]*0 + red[h-1][w+1]*-1 +
						red[h  ][w-1]*2 + red[h  ][w]*0 + red[h  ][w+1]*-2 +
						red[h+1][w-1]*1 + red[h+1][w]*0 + red[h+1][w+1]*-1;					
					if(r < 0) r = -r; // 음수가 나오면 양수로 바꿔주고,
					if(r > 255) r = 255; // 값의 크기가 255를 넘지 않게 함
					
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
			
			// 입력 이미지로부터 픽셀값 가져오기
			for(int h=0; h<height; h++) {
				for(int w=0; w<width; w++) {
					red[h][w] = Color.red(output.getPixel(w, h));
					green[h][w] = Color.green(output.getPixel(w, h));
					blue[h][w] = Color.blue(output.getPixel(w, h));
				}
			}
						
			// 출력 이미지에 픽셀값 채워넣기
			for(int h=1; h<height-1; h++) { // 이미지의 가장자리는 제외함
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
					
					if(sum > 100) { // 엣지부분은 검은색으로 칠함
						output2.setPixel(w, h, Color.rgb(0, 0, 0));
					} 
					else { // 엣지가 아닌 부분은 색을 단순화 시켜서 칠함						
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
