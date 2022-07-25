package com.example.myWeight;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class tips extends AppCompatActivity  {
   ListView listView ;
   ViewFlipper flipper;
   String mjudul[]= {
        "Air putih", "Olahraga teratur"
           , "Konsumsi protein", "Makanan kaya serat",
           "Konsumsi karbohidrat kompleks", "Istirahat cukup"
           , "Hindari makan malam", "Makan tepat waktu", "Berfikir positif"};
   String mdeskripsi[]={
           "Mengkonsumsi air putih akan meningkatkan hidrasi sel-sel tubuh yang akan menunjang proses metabolisme. Sebaiknya minumlah air putih minimal 8 gelas sehari.",
           "Jika Anda sangat sibuk, satu jam sekali bangkitlah dari tempat duduk lalu jalan di ruang kerja. Lakukan streeching dengan menggerakan tubuh ke kiri dan kanan. Hal ini selain akan memperlancar peredaran darah,  akan membantu membakar kalori dalam tubuh.",
           "Protein memiliki fungsi yang luar biasa yaitu mampu membakar lemak dan cocok sebagai penunjang diet sehat Anda.Protein bisa diperoleh dari putih telur, selain itu bisa juga di dapat dari oat meal gandum maupun sereal.",
           "Makan berserat tinggi ini bisa didapat dari buah dan sayur sayuran. Misalnya pir, pisang, pepaya, dan jambu biji.",
           "Program penurunan badan wajib mengganti kabohidrat yang kompleks seperti beras merah, gandum, oat meal, sereal, dan jenis kacang-kacangan.",
           "Pola hidup yang sehat berhubungan dengan pola tidur, normalnya manusia tidur 8 jam sehari atau minimal 7 jam sehari. ",
           "Sebaiknya hindari makan malam dengan makanan yang mengandung karbohidrat.",
           "Kebanyakan orang yang telat makan akan merasa lebih lapar saat makan sehingga sulit menahan diri dan akhirnya makan dalm porsi lebih banyak dari yang biasanya.",
           "Cobalah menyingkirkan pikiran negatif  dan mulailah berpikiran positif. Nikmatilah olahraga yang Anda lakukan.",
   };

   int mimage[]= {R.drawable.air, R.drawable.olahraga,R.drawable.protein,
           R.drawable.serat,R.drawable.karbo,R.drawable.tidur,R.drawable.makanmalam,
           R.drawable.waktu,R.drawable.positif};

   @Override
   protected void onCreate (Bundle savedInstanceState){
      super.onCreate(savedInstanceState);
      setContentView(R.layout.tips);

      flipper = (ViewFlipper)findViewById(R.id.galeri);

      for (int i = 0; i<mimage.length; i++){
         showImage(mimage[i]);
      }

      setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
      listView = findViewById(R.id.list_view);
      MyAdapter adapter = new MyAdapter( this, mjudul, mdeskripsi, mimage);
      listView.setAdapter(adapter);
   }

   public void showImage(int glr) {
      ImageView glrview = new ImageView(this);
      glrview.setBackgroundResource(glr);

      flipper.addView(glrview);
      flipper.setFlipInterval(3000);
      flipper.setAutoStart(true);

      flipper.setInAnimation(this, android.R.anim.slide_in_left);
      flipper.setInAnimation(this, android.R.anim.slide_out_right);
   }

   class MyAdapter extends ArrayAdapter <String> {

   Context context;
   String rJudul[];
   String rDeskripsi[];
   int rGambar[];

   MyAdapter (Context c, String judul[], String deskripsi[], int gambar[]) {
      super(c,R.layout.isitips, R.id.isijudul, judul);
      this.context = c;
      this.rJudul = judul;
      this.rDeskripsi = deskripsi;
      this.rGambar = gambar;
   }

      @NonNull
      @Override
      public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
         LayoutInflater li = (LayoutInflater)getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
         View it = li.inflate(R.layout.isitips, parent, false);
         ImageView gambar = it.findViewById(R.id.isigambar);
         TextView judul = it.findViewById(R.id.isijudul);
         TextView deskripsi = it.findViewById(R.id.deskripsi);

         gambar.setImageResource(rGambar[position]);
         judul.setText(rJudul[position]);
         deskripsi.setText(rDeskripsi[position]);

         return it;
      }
   }

}

