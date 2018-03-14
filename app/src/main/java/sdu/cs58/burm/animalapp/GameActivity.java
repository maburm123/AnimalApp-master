package sdu.cs58.burm.animalapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.Collections;

public class GameActivity extends AppCompatActivity {
    //ประกาศตัวแปรนะจ้ะ
    Button btn1,btn2,btn3, btn4;
    ImageView questionImageView;
    ImageButton volumnImageButton;
    MediaPlayer mediaPlayer; //เล่นเสียง
    int questionCount = 1; //เก็บจำนวนขอคำถาม
    ArrayList<Integer> qID = new ArrayList<Integer>();//ตัวแปรqid ไว้สำหรับ random คำถาม ชนิด arrayแบบintegerด้วย
    String answer;//เก็บคำตอบชนิด string
    int score = 0;//คะแนน

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        //ผูกอีลีเม้นกับตัวแปรบนjava
        btn1 = findViewById(R.id.btn1);
        btn2 = findViewById(R.id.btn2);
        btn3 = findViewById(R.id.btn3);
        btn4 = findViewById(R.id.btn4);
        questionImageView = findViewById(R.id.imvQuestion);
        volumnImageButton = findViewById(R.id.imbVolumn);
        //แสดงคำถาม
        for (int i=1; i <= questionCount; i++) {
            qID.add(i);
        }
        Collections.shuffle(qID);
        setQuestion(qID.remove(0));
        }//end oncreate method

    private void setQuestion(int qID) { //method serQuestion ใช้สำหรับกำหนดคำถามและเฉลยแต่ละข้อ
        if (qID == 1) {
            answer = "นก";
            questionImageView.setImageResource(R.drawable.bird);
            mediaPlayer = MediaPlayer.create(this, R.raw.bird);

            ArrayList<String> choice = new ArrayList<String>(); //กำหนดการrandom choice
            choice.add("นก");
            choice.add("ช้าง");
            choice.add("ยุง");
            choice.add("วัว");
            Collections.shuffle(choice);
            btn1.setText(choice.remove(0));
            btn2.setText(choice.remove(0));
            btn3.setText(choice.remove(0));
            btn4.setText(choice.remove(0));
        }
    } //end setquestion method

    public void choiceAns(View view) { //ตรวจคำตอบ
        Button button = (Button) view;
        String buttonString = button.getText().toString(); //เอาข้อความบนปุ่มมาเก็บไว้บนตัวแปรbutton string
        if (buttonString.equals(answer)) {
            score++; //เลือกคำตอบถูก+1
        }
        if (qID.isEmpty()) {//ถ้าทำครบทุกข้อ
            dialogboxScore();//แสดงคำแนนรวม
        }   else {//ถ้ายังทำไม่ครบทุกข้อ
            setQuestion(qID.remove(0));//เรียกmethod setQuestion แสดงคำถามต่อไป
        }
    } //end method choiceAns

    private void dialogboxScore() {//method สำหรับแสดงคะแนน
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("สรุปคะแนน");
        builder.setMessage("ได้คะแนน " + score + " คะแนน")
            .setCancelable(false)
            .setPositiveButton("ออกจากเกม", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    finish();//ออกจากแอพ
                }
            })
            .setNegativeButton("เล่นอีกครั้ง", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = getIntent();
                    finish();
                    startActivity(intent);
                }
            });
        AlertDialog alertDialog = builder.create();//สร้างdialogตามที่กำหนด
        alertDialog.show();

    }//end method dialogboxScore

    public void playSound(View view) {
        mediaPlayer.start();
    }//end playsound method

}//end class

