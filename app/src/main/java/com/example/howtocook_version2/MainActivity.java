package com.example.howtocook_version2;


import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    Button home_btn, fav_btn, myrep_btn;
    Button cate1, cate2, cate3, cate4, cate5;
    EditText editText;
    FloatingActionButton danbee_btn;

    private DBOpenHelper mDbOpenHelper;

    ImageButton button_food;
    AutoCompleteTextView autoFoodName;
    private List<String> list;
    private List<String> id_list;

    private Cursor mCursor;
    String food_name;
    String food_id;

    ImageButton button_cate1;
    ImageButton button_cate2;
    ImageButton button_cate3;
    ImageButton button_cate4;
    ImageButton button_cate5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        //action bar 없애기
        getSupportActionBar().hide();

        //최조 실행 여부 판단
        SharedPreferences pref = getSharedPreferences("checkFirst", Activity.MODE_PRIVATE);
        boolean checkFirst = pref.getBoolean("checkFirst",false);
        if(checkFirst == false){
            //앱 최초 실행 시 하고 싶은 작업

            mDbOpenHelper = new DBOpenHelper(this);
            mDbOpenHelper.open();

            mDbOpenHelper.insertColumn("김치찌개", "배추김치70g,두부1/4모,파10g,다시마 20g,물 600ml","1. 냄비에 다시마와 물을 넣고 10분간 끓인 후 다시마를 건져줍니다.\n2. 긇는 육수에 배추김치와 고춧가루, 다진마늘을 넣고 10분간 더 끓여줍니다.\n3. 적당한 크기로 썬 두부,파를 넣고 5분간 더 끓여줍니다.\n4. 부족한 간은 소금으로 해주어 완성합니다.","3","http://image.auction.co.kr/itemimage/1b/f3/94/1bf39477a6.jpg");


            mDbOpenHelper.insertColumn("김치볶음밥", "쌀100g,배추김치60g,올리브유3큰술,참기름1큰술,다진마늘1작은술","1. 김치를 작게 썰고 프라이팬에 올리브유를 두르고 다진마늘과 함께 볶아줍니다.\n2. 김치가 적당히 익으면 밥도 함께 넣어 볶아줍니다.","5","https://image9.coupangcdn.com/image/retail/images/59591855756534-62a29cde-e383-47cb-b3a5-9a075a58c7ad.jpg");


            mDbOpenHelper.insertColumn("김치전", "김치 50g, 밀가루 1컵, 부침가루 0.5컵, 계란 1개, 물 400ml,소금 1작은술","1. 달걀에 물을 붓고 잘 섞은 다음 밀가루,부침가루를 넣고 멍울 없이 저어줍니다.\n2. 잘게 자른 김치를 반죽에 섞어줍니다.\n3. 기호에 따라 반죽에 고기 또는 해물을 넣어줍니다.\n4. 뜨겁게 달군 팬에 기름을 넉넉히 두르고 반죽을 한 국자씩 떠서 앞뒤로 노릇하게 구워줍니다.","2","https://post-phinf.pstatic.net/MjAxOTA2MjZfMjQ2/MDAxNTYxNTE2OTcwNDk3.k9F_ctcO-NR8f5AGc7P2vTILoH9DLMncnxZ7LiNj-PEg.aR5jU4mh3YddnHG5JwRUiFb7UIoNH935HpNzZpg13tgg.JPEG/GettyImages-a10975620.jpg?type=w1200");



            mDbOpenHelper.insertColumn("불고기", "돼지고기300g,양파1/3개,풋고추1개,당근1/4개,파10g,간장3큰술,설탕2큰술,다진마늘1큰술,생강0.5작은술","1. 돼지고기는 적당한 크기로 썰어 청주, 후춧가루를 넣고 10분간 재워줍니다.\n2. 볼에 간장,설탕,다진마늘,생강을 넣어 섞어줍니다.\n3. 달군 팬에 식용유를 두르고 양념한 고기를 넣어 중불로 볶아줍니다.\n4. 고기가 거의 다 익으면 양파,당근,파,풋고추를 넣고 3분간 더 볶아줍니다.","2","https://recipe1.ezmember.co.kr/cache/recipe/2016/12/30/df939769792632a48a0eba8bc895e8601.jpg");



            mDbOpenHelper.insertColumn("제육볶음", "돼지고기150g,양파1개,식용유1큰술,파20g,고추장2큰술,고춧가루1큰술,간장1큰술,설탕1큰술,참기름 0.5큰술,다진 마늘 0/5큰술,다진생강0.5큰술","1. 그릇에 양념장 재료를 넣고 잘 섞어줍니다.\n2. 양파는 두껍게 채 썰고, 파는 3cm 길이로 썰어줍니다.\n3. 팬을 달군 후 기름을 두르고 양파를 넣고 살짝 볶습니다.\n4. 돼지고기를 넣고 양파와 함께 앞뒤를 익혀줍니다.\n5. 약한 불로 줄이고 볶은 야채와 돼지고기에 양념장을 넣고 볶아줍니다.","2","https://recipe1.ezmember.co.kr/cache/recipe/2015/05/27/38013d1dfd8fa46a871b9cda074b26341.jpg");



            mDbOpenHelper.insertColumn("돼지고기김치찌개", "돼지고기 앞다리살 200g, 김치 1줌, 대파, 된장 1/2스푼, 쌀뜨물 2컵, 고추가루 1스푼, 다진마늘, 간장 1스푼","1. 대파, 김치, 돼지고기를 먹기 좋게 썰어줍니다.\n2. 냄비에 쌀뜨물을 올리고 물이 끓으면 돼지고기를 넣어줍니다.\n3. 된장, 김치, 고춧가루, 다진마늘, 간장을 넣고 끓여줍니다.","3","https://cdn.shopify.com/s/files/1/0367/0533/7388/products/2_732ac88a-bd53-4678-a4d7-d98fe4cfc23d_800x.png?v=1585998665");



            mDbOpenHelper.insertColumn("갈비찜", "소갈비300g,무40g,당근1/4개,양파1/2개,은행5개,표고버섯1개,밤2개,대추2개,파10g간장2큰술,물200ml,정종1큰술,설탕 1큰술,다진마늘0.5큰술,후춧가루¼작은술,참기름1큰술","1. 갈비는 찬물에 2시간 이상 담가 핏물을 빼줍니다.\n2. 소뼈에 붙어 있는 기름을 제거합니다.\n3. 갈비를 3분 정도 데친 뒤 찬물에 헹궈줍니다.\n4. 그릇에 양념장 재료와 갈비를 넣고 냉장고에서 10시간 정도 숙성시킵니다.\n5. 양파, 무, 당근은 한 입 크기로 썰어줍니다. 표고버섯은 기둥을 제거한 뒤 굵게 채썰어 줍니다.\n6. 갈비와 양념장을 큰 냄비에 옮겨 끓여줍니다. 끓기 시작하면 약한 불로 줄여 50분간 더 익혀줍니다.\n7. 고기가 다 익어 갈 때쯤 무,당근,양파,은행,표고버섯, 밤,대추를 넣고 20분간 더 끓입니다.","2","https://recipe1.ezmember.co.kr/cache/recipe/2017/09/15/af5ed61b01ce6d0c8ded20d59f0d15e31.jpg");


            mDbOpenHelper.insertColumn("스테이크", "쇠고기안심300g,올리브유3큰술,양송이버섯2개,다진 마늘1/2큰술,후추,버터3큰술,양파3/4개","1. 양파, 버섯을 썰어주고 올리브올과 버터에 볶아줍니다.\n2. 소고기는 앞뒤로 소금,후추를 뿌리고 올리브유를 두른 팬에 구워줍니다.\n3. 소고기 옆에 볶은 야채를 올려주고 기호에 맞게 소스를 뿌립니다.","2","https://lh3.googleusercontent.com/proxy/fRhyjXpmEYKIQVlj8DFTQgVgGHvWnLYO4jmOhY34oZ-mUiXKctdp4SXi_StPgdKLSgocdzqb_cKlAjCAfxzpEsrgtzqUsq-qxsRn4FcfrxFvLf_Jqg5BlQzdBkhruV12vA");



            mDbOpenHelper.insertColumn("소고기무국", "소고기부채살40g,무70g,파20g,물400ml,참기름1큰술,국간장1큰술,다진마늘0.5큰술,소금0.5큰술","1. 소고기는 핏물을 닦고 청주1큰술,다진마늘0.5큰술,후춧가루를 뿌려 양념해줍니다.\n2. 냄비에 참기름을 두르고 양념한 소고기를 넣어 중불에서 2분간 볶아줍니다.\n3. 고기가 다 익으면 적당한 크기로 썬 무를 넣고 볶다가 물을 부어 끓여줍니다.\n4. 무가 투명해지면 국간장,다진마늘,소금을 넣고 5분간 더 끓여줍니다.","3","https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F235F8743523D9CE126");


            mDbOpenHelper.insertColumn("삼계탕", "영계1마리,찹쌀40g,황기4뿌리,물800g,수삼4뿌리,마늘4개,대추4개,달걀1개,소금1큰술,파 20g","1. 냄비에 황기와 물을 붓고 센불에 12분 정도 올려 끓으면, 중불로 낮추어 40분 정도 끓인 다음 체에 밭쳐 황기물을 만들어 줍니다.\n2. 영계의 뱃속에 찹쌀과 수삼·마늘·대추를 넣고, 내용물이 나오지 않도록 닭다리를 엇갈리게 끼워줍니다.\n3. 냄비에 영계와 황기물을 붓고, 센불에 20분 정도 올려 끓으면 중불로 낮추고 소금과 후춧가루로 간을 맞추고 10분 정도 끓입니다.","3","https://lh3.googleusercontent.com/proxy/wB00rJKwpMr3x34gc4CiLDl_mj_tHaj6RYtYgMB1jFo7huS0s9eWf7ohQXSaqZBzdBa2CZ6uJ9-fFpj6zKdPlcIRpBf9CVvkYYc6SKVZUJAu-CLB");


            mDbOpenHelper.insertColumn("닭볶음탕","닭 1마리,감자 1개,당근 1/2개,양파 1/2개, 파60g,물700ml,고춧가루3큰술,고추장3큰술, 간장2큰술,청주2큰술,설탕1큰술,물엿2큰술,다진마늘1큰술,다진생강1작은술","1. 닭을 찬물에 씻어 건진 후 물기를 걷고 청주, 소금, 후춧가루로 밑간하여 20분 동안 재워줍니다.\n2. 감자와 당근은 깍둑 썰고, 양파는 두껍게 채썰고 파는 5cm 길이로 썰어줍니다.\n3. 볼에 고추장,고춧가루,간장,설탕,물엿,다진마늘,다진생강을 넣어 양념을 만들어줍니다.\n4. 냄비에 닭,감자,당근,양파,파,양념장, 물을 넣고 센불에서 끓이다 중간 불로 줄여 20분 동안 끓여줍니다.","2","https://t1.daumcdn.net/cfile/tistory/21323847550ECD3D26");


            mDbOpenHelper.insertColumn("닭봉", "닭봉 20개, 우유, 대파 1/2대, 소주 1/2컵, 간장 5숟가락, 설탕 2숟가락, 식초 2숟가락, 청주 2숟가락, 케첩 1숟가락, 물엿 1숟가락","1. 닭봉이 잠기게 우유를 부은 후 30분간 재워 잡내를 제거해줍니다.\n2. 30분 후, 우유를 헹궈내고 체에 밭쳐 물기를 빼주세요.\n3. 팔팔 끓는 물에 닭과 소주 반컵을 넣고 3분간 데친 후 하나하나 씻어줍니다.\n4. 팬에 간장 5, 설탕 2, 식초 2, 청주 2, 케첩 1, 물엿 1을 넣어 섞은 다음, 닭과 대파를 넣고 20분간 조려줍니다.","1","https://recipe1.ezmember.co.kr/cache/recipe/2017/07/20/261b2101eaa6d10b1b09ba5105b5986b1.jpg");


            mDbOpenHelper.insertColumn("고등어구이", "고등어1마리,소금1큰술,식용유 0.5큰술","1. 껍질 쪽에 칼집을 서너 개 넣고 소금을 고루 뿌려 냉장실에 2시간 정도 둡니다.\n2. 소금에 절인 고등어의 물기를 키친타월로 흡수합니다.\n3. 팬을 예열하여 식용유를 바른 다음 고등어 껍질이 위로 향하게 놓고 앞뒤로 노르스름하게 굽는다.","1","https://cdn.crowdpic.net/detail-thumb/thumb_d_26CD0FD0F18B43FF18DD846B31032BA8.jpg");


            mDbOpenHelper.insertColumn("고등어튀김", "고등어1마리,밀가루8g,식용유1/2컵,달걀1개,소금1작은술","1. 고등어에 밀가루와 달걀물을 순서대로 묻여줍니다.\n2. 달궈진 후라이팬에 식용유를 두르고 튀겨줍니다.","1","https://mblogthumb-phinf.pstatic.net/MjAxODA3MDVfMjc5/MDAxNTMwNzgyNzUyNjEw.L0YjlKHs1KV_IhdAq9_tvGEmMUmJ4rWrt_ZhpSQW8gsg.hZkQTloslMa_5tOujjcjhX3TSi3y-yYO_r-o4n9VNJEg.JPEG.ssuni5296/image_5128755351530782464461.jpg?type=w800");



            mDbOpenHelper.insertColumn("고등어조림", "고등어 1마리, 무 500g, 대파 1뿌리, 물 300ml, 진간장 4큰술, 고추장 2큰술, 고춧가루 1큰술, 매실액 2큰술, 맛술 2큰술, 올리고당 1큰술, 다진 마늘 1작은술, 후춧가루 약간","1. 무와 대파는 흐르는 물에 씻은 후 무는 도톰하게 반달 썰어 주고 대파는 어슷 썰어 줍니다.\n2. 고등어는 흐르는 물에 깨끗하게 씻어 줍니다.\n3. 분량의 양념장 재료 진간장, 고추장, 고춧가루, 매실액, 맛술, 올리고당, 다진 마늘, 후춧가루를 넣어 고루 섞어서 양념장을 만들어 줍니다.\n4. 냄비 바닥에 무를 깔고 씻어 놓은 고등어를 올린 후 대파를 얹어 주고 양념장을 끼얹어 준 다음 물을 부어 줍니다.\n5. 가스 불에 올려 센 불에서 끓여 주다가 한소끔 끓으면 불을 줄이고 간이 고루 배도록 중간 중간 양념장을 끼얹어 주면서 조려 줍니다.","3","https://homecuisine.co.kr/files/attach/images/140/744/009/e382399431288b69da57cb0a73f79bee.JPG");



            mDbOpenHelper.insertColumn("연어샐러드", "연어100g,양상추1장,토마토1/2개,샐러리5cm,소금1작은술,드레싱","1. 연어는 1.5cm 크기로 썰어줍니다.\n2. 양상추는 흐르는 물에 씻어 물기를 털어내고 뜯습니다. 셀러리는 섬유질을 벗기고 어슷썰기를 합니다. 토마토는 4등분 해줍니다. \n3. 접시에 담고 준비해 둔 연어를 얹어 드레싱을 뿌립니다.","2","https://recipe1.ezmember.co.kr/cache/recipe/2018/11/09/17dfe208045e588b8f30f7f6a8dcb65c1.jpg");



            mDbOpenHelper.insertColumn("연어덮밥", "밥1공기,연어1조각,마늘2개,생강1/2개,양파1/2개,물300ml,청주1큰술,소금1작은술,간장1작은술,설탕1큰술,올리고당1큰술","1. 마늘과 생강은 얇게 편 썰고 양파는 2cm크기로 썰어줍니다.\n2. 연어는 2cm 크기로 썬 뒤 청주,소금,후추로 밑간해줍니다.\n3. 마늘,생강,간장,설탕,올리고당,소금,물을 냄비에 넣고 10분 정도 끓여줍니다.\n4. 팬에 기름을 두른 뒤 연어,양파를 넣고 볶아줍니다.\n5. 양파가 익으면 만들어 소스를 넣고 잘 저어 농도를 낸 뒤 밥 위에 얹어줍니다.","5","https://recipe1.ezmember.co.kr/cache/recipe/2017/12/12/8061d52b30f06835ffeead54dd9a04741.jpg");


            mDbOpenHelper.insertColumn("연어구이", "스테이크용연어 3쪽, 대파 1뿌리, 식용유 조금, 소금 조금, 간장 4큰술, 설탕 1큰술, 청주 2큰술, 물엿 1큰술","1. 간장 4큰술, 설탕 1큰술, 청주 2큰술, 물엿 1큰술를 모두 섞어 바글바글 끓여줍니다.\n2. 연어는 껍데기의 비늘을 숟가락으로 긁어 깨끗하게 헹군 뒤, 키친타월에 물기를 제거해줍니다.\n3. 팬에 식용유를 둘러 달군 뒤, 연어를 앞뒤로 노릇하게 구워줍니다.\n4. 연어에 끓여논 데리야키소스를 넣고 약불로 줄여 쫄여줍니다.","1","https://steptohealth.co.kr/wp-content/uploads/2019/04/Baked-salmon-576x384.jpg");


            mDbOpenHelper.insertColumn("참치마요덮밥", "참치캔1/2개, 밥1공기,마요네즈2큰술,간장1작은술,설탕1큰술,깨1작은술","1. 참치캔에서 참치를 덜어 기름을 꼭 짜줍니다.\n2. 밥을 담은 그릇에 참치를 올립니다.\n3. 참치 위에 마요네즈,간장,설탕,깨를 올리고 섞어줍니다.","5","https://recipe1.ezmember.co.kr/cache/recipe/2016/09/01/484b1194a69d0b2da09014a25a9334de1.jpg");


            mDbOpenHelper.insertColumn("참치죽", "참치 통조림1/2캔,쌀40g,물300ml,소금 1/2큰술,당근10g,참기름","1. 쌀은 잠길 만큼 물을 붓고 1시간 정도 불려줍니다.\n2. 달군 냄비에 불린 쌀을 투명해질때까지 볶아주다 물을 부어줍니다.\n3. 잘게 다진 당근을 넣고 농도가 날때까지 약불로 끓여줍니다.\n4. 마지막으로 참치,참기름를 넣고 1분간 더 끓여줍니다.","5","https://lh3.googleusercontent.com/proxy/78wExEpukuJiYDOJynSj5oQSQC438AwGVywFAHn0EyBQTxyo9VknZ8j_lFTr5t5e7W92pgrt6869pnCxsXAYqWXQ9ZxeeIPi7pncktfHd5cN264ImkCN_ks1r75gZ43Ap-SWkcELYE844F0-2wGtPGro1x1KBKJHjfcyE-DyuoS6riW24mIMqVCpbq322A9AF5ybz41BDmwfnuZWQBPRGVg9");


            mDbOpenHelper.insertColumn("참치짜글이", "마늘 1알, 양파 1개, 두부 1/4모, 참치 통조림 1캔, 스위트콘, 김치, 쌀뜨물, 간장 1큰술, 설탕 1큰술, 고춧가루 1큰술, 고추장 1큰술","1. 양파와 두부를 먹기 좋게 썰어주고, 마늘을 다져줍니다.\n2. 참치의 기름을 제거해줍니다.\n3. 냄비에 기름을 두르고 마늘을 볶아줍니다.\n4. 참치와 김치를 넣고 볶아줍니다.\n5. 쌀뜨물과 간장1큰술, 고추장1큰술, 고춧가루1큰술, 설탕1큰술을 넣고 졸여줍니다.","3","https://static.hubzum.zumst.com/hubzum/2017/06/20/11/7e973f91f90f473f9bbd7baac2b41d4d_780x0c.jpg");


            mDbOpenHelper.insertColumn("새우튀김", "새우3마리,밀가루1큰술,달걀1개,얼음물50ml,식용유80ml,소금1작은술","1. 새우는 꼬리와 껍질을 제거하고 소금을 뿌려 10분간 재워줍니다.\n2. 볼에 얼음물,달걀,밀가루를 넣고 섞어 튀김옷을 만들어줍니다.\n3. 새우에 튀김옷을 골고루 묻힙니다.\n4. 냄비에 식용유를 붓고 160˚C로 예열 후 튀깁니다.","1","https://lh3.googleusercontent.com/proxy/Bv--57AapARqoo_1U5VlAri7MOJi2gP7bv7ZTVE538Ywgtu3Xdh7v8OahDFRDhObJzjKh5TMkPTUT6S--_drK7e08IIRrl79hfup9NxzJllFqVF8REp9G_zVTcq1_p1S-dbk6QWGhgP2yj52iav7UGV9Ug");


            mDbOpenHelper.insertColumn("감바스알아히요", "칵테일새우10개,마늘5개,페페론치노5g,올리브오일100ml,소금1작은술","1. 새우을 제거한 후 소금으로 밑간을 합니다.\n2. 냄비에 올리브 오일을 넣고 잘게 썬 마늘과 페페론치노를 넣고 중불로 끓입니다.\n3. 올리브 오일이 끓기 시작하면 새우를 넣어 약불로 10분간 끓입니다.","2","https://upload.wikimedia.org/wikipedia/commons/6/65/Gambas_al_ajillo.jpg");


            mDbOpenHelper.insertColumn("새우볶음밥", "밥 1공기, 계란 2개, 대파 1/2개, 냉동새우 8마리, 간장 1t, 참기름 1t, 통깨 살짝","1. 팬에 올리브 기름을 살짝 두르고 파를 볶아 파기름을 준비해 주세요.\n2. 팬에 계란을 올려 스크램블을 만들어주세요.\n3. 프라이팬 모퉁이에 새우를 볶아주세요.\n4. 간장, 참기름, 밥을 넣고 볶아주세요.\n5. 그릇에 담아 통깨를 살짝 뿌려주세요.","5","https://recipe1.ezmember.co.kr/cache/recipe/2015/06/18/58d359a9ac359adbc4c7fa2603f2e504.jpg");


            mDbOpenHelper.insertColumn("토마토스파게티", "스파게티면150g,양파1/4개,파프리카1/10개,양송이버섯1개,마늘2개,토마토300g,소금1작은술,올리브유1큰술","1. 양파,파프리카,양송이버섯을 잘개 썰어줍니다.\n2. 마늘과 토마토를 으깨줍니다.\n3. 달군 팬에 올리브유를 두르고 썰어준 야채와 마늘,토마토를 넣어 볶아 소스를 만들어줍니다.\n4. 끓는 물에 소금을 약간 뿌리고 스파게티면을 8분간 삶아줍니다.\n5. 삶은 스파게티를 만든 소스에 넣고 버무립니다.","4","https://i.ytimg.com/vi/cMP7eLVIO_U/maxresdefault.jpg");



            mDbOpenHelper.insertColumn("까르보나라", "베이컨4장,양파 1/2개,생크림 2컵,계란노른자 2개,치즈2장,스파게티면160g,올리브유2큰술,소금1작은술,후추0.5큰술","1. 베이컨은 1cm로 잘라 준비하고 양파는 채 썰어줍니다.\n2. 생크림에 계란 노른자,치즈를 넣고 섞어줍니다.\n3. 냄비에 물과 소금을 넣고 스파게티 면을 넣어 8분간 삶아줍니다.\n4. 팬에 올리브유를 두르고 양파와 베이컨을 넣어 볶습니다.\n5. 생크림과 스파게티 면을 섞어줍니다.","4","https://cloudfront.haemukja.com/vh.php?url=https://d1hk7gw6lgygff.cloudfront.net/uploads/direction/image_file/1628/pad_thumb_image.png&convert=jpgmin&rt=600");


            mDbOpenHelper.insertColumn("알리오올리오", "스파게티면 200g, 올리브오일, 마늘 10쪽, 파마산치즈, 페처론치노 소금, 파슬리","1. 물 1리터에 소금 한스푼을 넣고 끓인 뒤, 물이 끓으면 스파게티 면을 넣고 7~8분 정도 익혀주세요.\n2. 마늘과 페퍼론치노는 슬라이스 해주세요.\n3. 팬에 올리브오일 3~4큰술을 둘러준 뒤, 마늘과 페퍼론치노를 넣고 충분히 볶아주세요.\n4. 팬에 면을 넣고 면수를 조금씩 넣어 가면서 농도를 맞춰주세요.\n5. 마지막으로 파슬리가루와 파마산치즈를 넣고 마무리해주세요.","4","https://t1.daumcdn.net/cfile/tistory/997405415D20B31C16");


            mDbOpenHelper.insertColumn("잔치국수", "간장 2작은술,설탕 1작은술,다진마늘1작은술,참기름2작은술,애호박1/3개,계란 1개,소면 200g, 소금1작은술,멸치 20마리,다시마2장,물600ml","1. 냄비에 물,멸치,다시마,간장,소금,설탕,참기름을 넣고 10분간 끓여줍니다.\n2. 애호박을 채썰고 물기를 짠 후 팬에 볶습니다.\n3. 계란은 흰자와 노른자를 분리한 후 지단을 부쳐 채썰어줍니다.\n4. 끓는 물에 소면을 5분간 삶아주고 준비한 국물을 뿌리고 고명을 얹어줍니다.","4","http://www.palnews.co.kr/news/photo/201712/91750_24575_1653.jpg");



            mDbOpenHelper.insertColumn("비빔국수", "소면160g,애호박 1/5개,양파 1/8개,오이 1/4개,양배추1장,식용유0.5큰술,고추장 3큰술,간장1작은술,설탕2/3큰술,식초1큰술,참기름1작은술","1. 끓는 물에 소면을 펼쳐 넣고 5분간 삶아줍니다.\n2. 삶은 소면은 찬물에 담가 전분기를 뺀 후 체에 밭쳐 물기를 뺀다.\n3. 오이,양배추,애호박,양파는 가늘게 채 썬다.\n4. 달군 팬에 식용유를 두르고 채 썬 야채를 볶는다.\n5. 볼에 양념재료를 골고루 섞은 후 삶은 소면과 야채를 넣고 골고루 버무린다.","4","https://recipe1.ezmember.co.kr/cache/recipe/2018/01/19/9deb7510516f154f465f04aa46379d6e1.jpg");



            mDbOpenHelper.insertColumn("골뱅이소면", "골뱅이 1통, 양배추 1줌, 깻잎 10장, 오이 1개, 파프리카 조금, 고추장 4큰술, 간장 2큰술, 다진마늘 1큰술, 설탕 4큰술, 물엿 1큰술, 고추가루 2큰술, 매실액 1큰술, 참기름 1큰술, 식초 5큰술","1. 오이의 껍질을 벗긴 뒤,  파프리카, 양배추, 깻잎도 송송 썰어주세요.\n2. 고추장 4큰술, 간장2큰술, 설탕 4큰술, 물엿 1큰술, 고추가루 2큰술, 매실액1큰술, 참기름 1큰술, 식초 5큰술, 다진마늘 1큰술을 담아서 깨 한큰술과 섞어주세요.\n3. 골뱅이 한캔을 준비해 물에 헹궈주세요.\n4. 끓는 물에 국수를 삶은 뒤 찬물에 헹궈주세요.\n5. 양념장과 골뱅이, 손질 야채를 잘 무쳐 그릇에 담고 소면도 옆에 담아주세요.","4","https://www.cj.co.kr/images/theKitchen/PHON/0000002302/0000009585/0000002302.jpg");



            mDbOpenHelper.insertColumn("계란찜", "계란3개,다시마물1컵,소금2작은술","1. 계란을 풀고 알끈을 끊어줍니다.\n2. 계란물에 다시마물을 부어주고 그릇을 랩으로 감쌉니다.\n3. 냄비에 그릇이 반 잠길 정도로 물을 붓고 20분 정도 중탕으로 끓여줍니다.","1","https://i.ytimg.com/vi/ORNiBOoLwCY/maxresdefault.jpg");




            mDbOpenHelper.insertColumn("계란말이", "계란 4알, 물 50cc, 대파 1대, 소금 약간","1. 계란 4개와 물 50cc를 잘섞어주세요.\n2. 기름에 대파를 볶아 파기름을 내줍니다.\n3. 계란물을 1/3 혹은 1/4정도 부어주세요\n4. 계란물이 익으면, 한쪽으로 밀어두고 또 계란물을 부어줍니다.\n5. 계란이 도톰해질때까지 계란물을 부어주는 과정을 반복합니다.","1","https://recipe1.ezmember.co.kr/cache/recipe/2016/03/16/4b2ad9b10dd6253ae2236658bab43b0b1.jpg");




            mDbOpenHelper.insertColumn("계란죽", "찬밥 1공기, 물 2컵, 당근 다진거 2스푼, 양파 다진거 2스푼, 계란 1개, 참기름 1스푼, 깨 약간","1. 당근과 양파를 잘게 다져줍니다.\n2. 냄비에 다진야채들을 넣고 참기름 1스푼을 넣어 약불에서 볶아줍니다.\n3. 양파가 투명해지면 찬밥과 물2컵을 부어줍니다. \n4. 가스불을 최대로 올려서 팔팔 끓여줍니다.\n5. 계란을 넣고 휘저어줍니다.","5","https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcQY6vyFzo8RVuN7DAn2iW5vRNVHliR4zKB1DA&usqp=CAU");





            mDbOpenHelper.insertColumn("감자튀김", "감자1개,식용유600ml,소금1작은술","1. 감자는 껍질을 벗긴 후 1cm 두께로 썰어줍니다.\n2. 물에 소금을 섞고 10분간 감자를 넣어 전분기를 제거해줍니다.\n3. 감자를 키친타월 위에 올려 물기를 완전히 제거해줍니다.\n4. 냄비에 식용유를 붓고 160도로 예열한 후 감자를 넣고 10분간 튀겨줍니다. ","1","http://ejfoodstory.com/web/product/big/201704/216_shop1_475814.jpg");




            mDbOpenHelper.insertColumn("매쉬포테이토", "감자 4개, 마요네즈 5T, 버터 1T, 설탕 2T, 소금 1/3T","1. 감자의 껍질을 벗긴 후, 썰어줍니다.\n2. 냄비에 감자를 넣고 10분간 삶아줍니다.\n3. 숟가락으로 감자를 으깬 뒤, 마요네즈 5T, 버터 1T, 설탕 2T, 소금 1/3T를 넣고 섞어줍니다. ","1","https://recipe1.ezmember.co.kr/cache/recipe/2016/01/21/0ac6c7f68cf1f5d734733f9d6bbe14241.jpg");



            mDbOpenHelper.insertColumn("감자조림", "감자 3개, 양파 조금, 다진파(생략가능) 조금, 간장 3숟가락, 물엿 4숟가락, 식용유 2숟가락","1. 감자를 깍두기로 썰어준 뒤 물에 헹궈서 전분기를 빼줍니다.\n2. 간장.물엿.식용유를 먼저 넣고 물은 자작한 정도까지 넣어줍니다.\n3. 뚜껑 덮고 10분, 뚜껑 열고 5분을 졸여줍니다.","1","https://recipe1.ezmember.co.kr/cache/recipe/2018/08/01/0e11e5e8b813d841649cfd7669cb1c281.jpg");






            mDbOpenHelper.insertColumn("무국", "무50g,파20g,다진마늘1작은술,국간장1작은술,들기름1작은술,소금1작은술,물400ml","1. 무는 납작하게 썰고 소금물에 헹궈 건져 물기를 빼줍니다.\n2. 냄비에 국간장과 들기름, 다진 마늘, 무를 넣고 투명해질때까지 볶아줍니다.\n3. 물을 붓고 20분간 끓인 후 파를 채 썰어 넣어줍니다.\n4. 소금으로 간을 해서 완성한다.","3","https://storage.googleapis.com/cbmpress/uploads/sites/3/2019/03/IMG_6681_1.jpg");






            mDbOpenHelper.insertColumn("북엇국", "북어포15g,무20g,계란1개,파10g,홍고추1개,물400ml,참기름1큰술,국간장1큰술,소금0.5작은술","1. 찢은 북어를 찬물에 담갔다 바로 건져서 꼭 짜줍니다. \n2. 북어에 국간장 1큰술,참기름 1큰술,다진 마늘 0.5큰술,후춧가루를 뿌려 양념해줍니다.\n3. 냄비에 참기름을 두르고 양념한 북어를 중간 불에서 볶다가 적당한 크기로 채썬 무를 넣고 물을 부어 10분간 끓여줍니다.\n4. 국물이 뽀얗게 우러나면 국간장,소금,홍고추,파를 넣어줍니다.\n5. 계란을 풀어 넣어줍니다.","3","https://recipe1.ezmember.co.kr/cache/recipe/2016/10/24/61de32c7c9dd5855060f9316c1a96f0b1.jpg");






            mDbOpenHelper.insertColumn("무생채", "무 1.2kg, 대파 2대, 고추가루 6T, 설탕 3T, 액젓 5T, 소금 1T, 다진마늘 3T","1. 무를 얇은 두께로 채썰고, 대파를 어슷썰어줍니다.\n2. 고추가루, 설탕, 액젓, 다진마늘, 소금을 모두 넣고 잘 비벼주세요.\n3. 양념과 무가 잘 어울어지도록 계속 버물여줍니다.","1","https://recipe1.ezmember.co.kr/cache/recipe/2018/07/29/52046ea43391de69233f594b0b52bb311.JPG");






            mDbOpenHelper.insertColumn("떡볶이", "떡볶이떡 200g,어묵100g,물400ml,파20g,고추장 2큰술,설탕1큰술,간장0.5큰술,고춧가루 1작은술,다진마늘1작은술","1. 그릇에 고추장,설탕,간장,고춧가루,다진마늘을 넣고 잘 섞는다.\n2. 어묵은 한 입 크기로 썰어줍니다.\n3. 냄비에 물을 붓고 끓으면 어묵과 양념장을 넣는다.\n4. 떡,파를 넣고 중간 불에서 양념이 떡에 밸 때까지 조린다.","2","http://th3.tmon.kr/thumbs/image/3b7/a82/56a/5fcf6acb2_700x700_95_FIT.jpg");






            mDbOpenHelper.insertColumn("떡국", "떡국떡 2컵, 멸치육수 2컵, 대파 1대, 국간장 1큰술, 계란 1개, 깨가루 약간, 참기름 약간, 김가루 약간","1. 떡국 떡을 씻어 냄비에 담아줍니다.\n2. 대파, 멸치, 다시마, 물, 국간장을 넣고 끓여줍니다.\n3. 계란을 풀어 저어줍니다.\n4. 참기름, 깨, 김가루를 취향대로 올려줍니다.","3","https://post-phinf.pstatic.net/MjAxODEyMzBfMjg0/MDAxNTQ2MTAyOTc4MDkz.FVwCJBMTQ7CJv6nIsKBmJj9CkAlUVvV9BKmqBOWwk9Ag.3uIBQYYYnhW18reTBnj8Iismfa14ickofKmACWbXo2og.JPEG/1.jpg?type=w1200");




            mDbOpenHelper.insertColumn("떡튀김", "떡볶이떡 400g, 식용유 적당량, 고추장 1스푼, 케찹 3스푼, 올리고당(꿀) 2스푼","1. 떡볶이떡 400g 을 끓는물에 3분동안 데쳐준 뒤, 찬물에 헹궈 물기를 제거해줍니다.\n2. 식용유에 떡을 노릇노릇하게 구워줍니다.\n3. 케찹3스푼과,고추장1스푼,올리고당2스푼을 넣어 잘섞어서 떡튀김 소스를 만들어줍니다.","1","https://img-global.cpcdn.com/recipes/6e999e2b1ed2e091/751x532cq70/%EC%A7%91%EC%97%90%EC%84%9C-%EC%86%90%EC%89%BD%EA%B2%8C-%EB%A7%8C%EB%93%9C%EB%8A%94-%EB%96%A1%EA%BC%AC%EC%B9%98-recipe-main-photo.jpg");





            mDbOpenHelper.insertColumn("소시지야채볶음", "비엔나소시지10개,양파1/3개,파프리카1/3개,케첩2큰술,굴소스 1큰술,맛술1큰술,물엿1큰술,다진마늘1/2큰술","1. 비엔나소시지는 한쪽에만 칼집을 낸 후, 끓는 물에 넣어 30초 정도 데쳐줍니다.\n2. 양파와 파프리카는 한입크기로 썰어줍니다.\n3. 볼에 케첩,굴소스,맛술,물엿,다진마늘을 넣고 섞어줍니다.\n4. 달군 팬에 식용유를 두르고 비엔나소시지,양파,파프리카를 볶다가 양념을 뿌리고 5분간 더 볶아줍니다.","1","https://craftlog.com/m/i/5840734=s1280=h960");






            mDbOpenHelper.insertColumn("소떡소떡", "비엔나소시지 12개, 떡볶이떡 12개, 식용유 1/2컵, 꼬치6개, 설탕 2큰술, 진간장 1큰술, 올리고당 1큰술, 고추장 1큰술, 케첩 2큰술, 물 1/3컵","1. 떡을 끓는물에 2분 정도 데친 후 찬물에 헹구어 줍니다.\n2. 비엔나 소시지에 칼집을 내고 떡과 함께 꼬치에 꽂아줍니다. \n3. 팬에 기름을 충분히 두르고 소떡소떡을 튀기듯이 익혀줍니다.\n4. 설탕 2큰술, 진간장 1큰술, 올리고당 1큰술, 고추장 1큰술, 케첩 2큰술, 물 1/3컵을 넣고 졸여줍니다.","1","https://t1.daumcdn.net/cfile/tistory/99B09F395A2E33E41E");








            mDbOpenHelper.insertColumn("소시지볶음밥", "신김치, 대파 1대, 소시지 8개, 밥 1공기, 계란, 간장 3큰술, 고춧가루 1큰술, 설탕 0.3큰술","1. 김치볶음밥에 넣을 김치는 볼에 담아 가위로 잘게 잘게 잘라주세요.\n2. 대파는 송송 썰어주고, 소시지는 얇게 썰어주세요.\n3. 펜에 식용유를 두르고 미리 썰어 놓은 소시지와 대파를 노릇노릇하게 기름에 튀겨 주듯이 볶아주세요.\n4. 김치와 고춧가루, 간장을 넣고 섞어주세요.\n5. 계란후라이를 만들어서 올려주세요.","5","https://img1.daumcdn.net/thumb/R720x0.q80/?scode=mtistory2&fname=http%3A%2F%2Fcfile3.uf.tistory.com%2Fimage%2F237F3F3C53E16BA927B253");




            mDbOpenHelper.insertColumn("카레라이스", "카레가루20g,감자1/2개,당근1/4개,양파1/2개,닭고기50g,밥200g,올리브유3큰술,물200ml","1.양파,감자,당근을 썰어줍니다.\n2. 닭고기는 소금,올리브유를 골고루 뿌리고 재워줍니다.\n3. 볼에 물과 카레가루를 넣고 카레물을 만듭니다.\n4. 달군 팬에 올리브유를 넣고 감자,당근,양파,닭고기를 볶아줍니다.\n5. 카레물을 붓고 중불에서 10분간 끓여줍니다.","5","https://recipe1.ezmember.co.kr/cache/recipe/2017/08/16/7f695a6aefc9d2996312e5532e76b7391.jpg");




            mDbOpenHelper.insertColumn("카레볶음밥", "밥 1공기, 대파 1/3대, 후랑크소세지1개, 후추 약간, 카레가루 1숟가락, 식용유 적당량, 참기름 약간","1. 송송 썬 대파를 기름두른 팬에 볶아 향을 내줍니다.\n2. 동글동글 썬 소세지와 후추를 넣어 볶아줍니다.\n3. 밥을 넣고 카레가루를 넣어 간을 맞춘 후 참기름으로 고소함을 더해줍니다.","5","https://asset02.flavr.co.kr/media/catalog/product/i/m/img_1694.jpg");




            mDbOpenHelper.insertColumn("카레우동", "우유 2컵, 카레가루 3큰술, 우동사리 1봉","1. 냄비에 우유 2컵(500ml 짜리 하나 가능) 을 붓고 카레가루 3큰술을 섞어주세요.\n2. 카레우유가 끓어오르면 면을 넣고 볶아줍니다.","4","https://lh3.googleusercontent.com/proxy/SxXkBE4MpTknaUIghImnaO68NNAdZ05WGAMwcydKo3XM1E6dNAdWGPaxv2YWy_P3lgAo3VwaqUg1XSYJTfh_FuMHLXoMel_cq--dPSH2pyW-QUzCJZh90lCSt67SOVvrtihs5CjkxgDCA9DFJ6VDuT5znKcx8m7nFXhJxA");





            mDbOpenHelper.insertColumn("양배추샐러드", "양배추2장,방울토마토5개,당근1/3개,샐러리10cml,드레싱,올리브유1큰술","1. 야채는 가늘게 채썰고 방울토마토는 2등분해줍니다.\n2. 볼에 야채,토마토,드레싱,올리브유를 넣고 골고루 섞는다.","1","https://i.pinimg.com/474x/c6/7e/91/c67e917c3f183f436fda8c4bc54b2e25.jpg");






            mDbOpenHelper.insertColumn("양배추토마토수프", "다진 마늘 1T, 양배추 1/4개, 토마토 2개, 양파 1개, 당근 1/2개, 감자 1개, 소금 1 t  ,후추 1ts, 대파 1줄기","1. 양배추, 양파, 당근, 감자를 먹기 좋게 썰어줍니다.\n2. 팬에 기름을 두르고 다진마늘과 야채를 넣고 볶아줍니다.\n3. 팬에 후추, 토마토, 대파를 넣어줍니다.\n4. 소금과 후추로 간을 해줍니다.","3","https://recipe1.ezmember.co.kr/cache/recipe/2017/02/03/6184e6f9a698ec8cd77de03f19daa2bf1_m.jpg");









            mDbOpenHelper.insertColumn("양배추쌈밥", "양배추, 참치쌈장, 밥 2인분, 참기름 한큰술, 통깨 한큰술, 소금 0.3티스푼","1. 양배추 가운데 있는 심을 자르고 먹기 좋은 크기로 잘라 찬물에 여러번 헹궈줍니다.\n2. 양배추를 전자레인지에 3분간 돌려준 뒤, 찬물에 행궈줍니다.\n3. 밥에 준비한 참기름, 통깨, 소금을 넣고 골고루 섞어줍니다.\n4. 김발에 양배추를 깔고 밥을 올린후 돌돌 말아줍니다.\n5. 돌돌 말아둔 양배추 쌈밥을 김밥처럼 썰고 그 위에 참치쌈장을 떠서 올려줍니다.","5","https://mblogthumb-phinf.pstatic.net/MjAxNzExMjRfMTE4/MDAxNTExNTA3MjQ3OTg2.0c_jS7utHJX9Rk6bdtn_e9bMIoMEi_MQ6kySoJ5qiMMg.QZYpHLlbXPzEcSJJmvep3FqvbNMRweSq7Meae26T6pYg.JPEG.elarpi/20171123_202058.jpg?type=w800");






            mDbOpenHelper.insertColumn("순두부찌개", "순두부150g,조갯살50g,청고추1개,파10g,물300ml,맑은국간장1큰술,소금0.5작은술,고춧가루1큰술,다진마늘1큰술,참기름1큰술","1. 조갯살은 소금물에 살살 씻어 체에 밭쳐 물기를 뺀다.\n2. 냄비에 순두부,맑은국간장,소금,물을 넣고 5분간 끓인다.\n3. 조갯살,고춧가루,다진마늘를 넣고, 5분간 더 끓인다.\n4. 청고추, 파, 참기름을 넣고 2분간 더 끓인다.","3","https://cloudfront.haemukja.com/vh.php?url=https://d1hk7gw6lgygff.cloudfront.net/uploads/direction/image_file/1131/pad_thumb_m.png&convert=jpgmin&rt=600");










            mDbOpenHelper.insertColumn("두부부침", "두부 1모, 들기름 3T, 대파 1/4개, 간장 2T, 설탕 1T, 식초 1T, 고춧가루 1t, 통깨 1t, 들기름 1t","1. 두부를 먹기 좋게 썰어줍니다.\n2. 팬을 달구고 들기름을 둘러주세요.\n3. 두부 양면이 노릇 노릇 해질때까지 부쳐주세요.\n4. 간장,설탕,식초,다진대파,고춧가루,통깨,들기름을 섞어 양념장을 만들어줍니다.\n5. 만들어진 양념장을 두부 위에 뿌려줍니다.","1","https://t1.daumcdn.net/cfile/tistory/99F91B3E5A3A6D2916");






            mDbOpenHelper.insertColumn("두부조림", "두부 반모, 간장 1큰술, 굴소스 1티스푼, 올리고당 1큰술, 카놀라유 조금","1. 두부를 먹기 좋은 크기로 썰어 키친 타올로 물기를 제거해 주세요.\n2.팬에 기름을 두르고 앞 뒤 노릇노릇하게 구워줍니다.\n3. 간장,굴소스,올리고당,물1큰술을 넣고 고루고루 저어줍니다.\n4. 두부에 양념이 베이도록 졸입니다.","1","https://recipe1.ezmember.co.kr/cache/recipe/2017/11/29/8cf81c4472e34cb79e7d492f4023d1fd1.jpg");








            mDbOpenHelper.insertColumn("된장찌개", "느타리버섯20g,양파1/2개,애호박1/4개,두부1/4모,국물용멸치20마리,파10g,물 600ml,된장 2큰술,다진마늘0.5큰술","1. 냄비에 국물용 멸치와 물을 넣고 10분간 끓여줍니다.\n2. 된장,다진마늘을 넣고 풀어줍니다.\n3. 끓는 된장물에 적당한 크기로 썬 양파,버섯,애호박,두부를 넣어줍니다.\n4. 10분간 더 끓이고 부족한 간은 소금으로 해주어 완성합니다.","3","https://recipe1.ezmember.co.kr/cache/recipe/2016/06/08/24c312f82313faaf1e4d5ef98761efcb1.jpg");









            mDbOpenHelper.insertColumn("취나물무침", "생취나물, 참기름, 액젓, 다진마늘, 참깨","1. 취나물 한다발을 끓는 물에 살짝 데쳐줍니다.\n2. 다진 마늘 약간, 참기름, 참깨, 액젓을 넣어줍니다.\n3. 양념을 취나물에 조무린 후 불에 살짝 볶아줍니다.","1","https://i.ytimg.com/vi/sn7lTRDAMs8/maxresdefault.jpg");




            mDbOpenHelper.insertColumn("배추된장국", "양배추 1/3통, 쌀뜨물 800ml, 된장 1숟가락, 다진마늘 1/2숟가락","1. 흐르는 물에 잘 씻은 양배추 1/3통을 먹기 좋은 한입사이즈로 뜯어준다음, 쌀뜬 물 800ml를 부워서 팔팔 끓여줍니다.\n2. 거기에 다진마늘 1숟가락과 된장 한숟가락 크게 떠서 풀어줍니다.","3","http://image.yes24.com/blogimage/blog/s/u/sunjoo1609/20160229105735755038.jpg");




            mDbOpenHelper.insertColumn("콩나물국", "콩나물 50g, 국물용 멸치 30마리, 파 10g, 물 400ml,다진마늘 1큰술, 소금 1큰술, 고춧가루 1작은술","1. 냄비에 국물용 멸치와 물을 넣고 10분간 끓여줍니다.\n2. 콩나물을 넣고 뚜껑을 덮어 콩나물이 익을 때까지 끓입니다.\n3. 파,다진마늘,고춧가루,소금을 넣고 2분간 더 끓입니다.","3","https://t1.daumcdn.net/cfile/tistory/99D4333A5D67CCC329");





            mDbOpenHelper.insertColumn("콩나물무침", "콩나물50g,국물용멸치30마리,파10g,물400ml,다진마늘1큰술,소금1큰술,고춧가루1작은술","1. 냄비에 국물용 멸치와 물을 넣고 10분간 끓여줍니다.\n2. 콩나물을 넣고 뚜껑을 덮어 콩나물이 익을 때까지 끓입니다.\n3. 파,다진마늘,고춧가루,소금을 넣고 2분간 더 끓입니다.","1","https://img1.daumcdn.net/thumb/R800x0/?scode=mtistory2&fname=https%3A%2F%2Ft1.daumcdn.net%2Fcfile%2Ftistory%2F27169B455965C0D418");






            mDbOpenHelper.insertColumn("콩나물찜", "콩나물 500g, 양파 1/2개, 대파 1대, 고추장 2큰술, 고춧가루 2큰술, 다진마늘 1큰술, 설탕 1큰술, 진간장 6큰술","1. 양파는 채 썰어주고 대파는 손가락 길이로 크게 잘라 반으로 갈라줍니다.\n2. 고추장 2큰술, 고춧가루 2큰술, 다진마늘 1큰술, 설탕 1큰술, 진간장 6큰술, 물 2큰술을 넣고 콩나물찜 양념을 만들어줍니다.\n3. 냄비에  물 1/2종이컵을 넣고 물이 끓으면 콩나물과 채소를 넣습니다.\n4. 양념장을 넣고 양념이 골고루 섞이게 해줍니다.\n5. 양념이 걸쭉해질때까지 쫄입니다.","1","https://recipe1.ezmember.co.kr/cache/recipe/2017/04/18/65d46410664a201e5ded9223af92a9b91.jpg");






            mDbOpenHelper.insertColumn("호박전", "애호박 2/3개,밀가루3큰술,달걀1개,식용유3큰술,다진마늘1/2작은술,소금1작은술","1. 애호박은 0.5cm 두께로 썬 후, 소금을 뿌려 10분간 절인 후 키친타월로 물기를 없애줍니다.\n2. 달걀은 달걀물을 만든 후 다진 마늘을 넣어 섞어줍니다.\n3. 애호박 앞뒤로 밀가루를 묻힌 후 달걀물을 묻힙니다.\n4. 달군 팬에 식용유 1큰술을 두르고 애호박을 올려 앞뒤로 굽습니다.","1","https://lh3.googleusercontent.com/proxy/9aIdyFWDvYnryZcEK7Iv4c_4rwCGm6MilZo_Kjo2AqLoM2l6ta8WUJ1ctvn-VL33w9q2kqYV_AtGx5_v8quGy_q6wzqui20MZ_QddgZ5EztWYkspkQOktKzf-EuPcag2kqsCjyM7lIeQRSbo7gDqvvEaDw");






            mDbOpenHelper.insertColumn("호박죽", "단호박 700g, 찹쌀가루 7숟가락, 물 4컵, 설탕 3숟가락, 소금 0.3숟가락","1. 단호박 꼭지가 아래로 가게 접시에 놓고 전자레인지에 3분간 돌려주세요.\n2. 반 갈라 단호박 씨를 긁어내고, 큼직하게 썰어 전자레인지에 5분 더 돌려주세요.\n3. 단호박 껍질은 제거한뒤, 믹서기에 단호박을 넣고 물 2컵을 부어 곱게 갈아 줍니다.\n4. 냄비에 간 단호박을 부어주고, 중불에 단호박 물이 끓어 오르면 찹쌀가루를 넣어줍니다.\n5. 소금과 설탕으로 간을 맞춰줍니다.","5","http://dalimjuk.com/web/product/big/201806/19_shop1_15278317569334.jpg");



            mDbOpenHelper.insertColumn("단호박찜", "단호박, 소금, 계란","1. 단호박의 윗부분을 칼로 제거하고 단호박 속을 수저로 파줍니다.\n2. 계란과 물을 동량으로 풀어준후 소량의 소금간을 합니다. (계란 1개일때 소금 완두콩알만큼)\n3. 준비한 계란물을 단호박속에 부어준후 뚜껑을 덮어줍니다.\n4. 전자렌지에 8분정도 돌려줍니다.","2","https://lh3.googleusercontent.com/proxy/HWXZdrO3wSf3d6QXZN2OfCA_OKO9N8xaLsglrai6BRBiysfXmtpVAko1J1V4XmMOQXT7AXRj0lFzBaE0LIkJYBwjj1vJRMQ3gdTMxMycPOtGJEwN1DmmH_0h7f6C6wc2o19ym85iiom5a5ZZQ2tlJItbZWArCqgz-L4MAM644");



            mDbOpenHelper.insertColumn("미역국", "마른미역5g,소고기사태30g,물400ml,맑은국간장1큰술,다진마늘1작은술,소금0.5작은술,참기름1큰술","1. 마른미역을 물에 30분 정도 불리고 깨끗이 씻어 물기를 짠 후, 길이 4cm 정도로 썰어줍니다.\n2. 소고기는 핏물을 닦고 청주1큰술,다진마늘0.5큰술,후춧가루를 뿌려 양념해줍니다.\n3. 냄비를 달구어 참기름을 두르고, 쇠고기를 넣어 중불에서 2분 정도 볶다가, 불린 미역을 넣고 3분 정도 더 볶아줍니다.\n4. 물을 붓고, 맑은국간장,다진마늘,소금을 넣고 5분간 끓여줍니다.","3","http://www.chungjungone.com/knowhow/images/blog/recipe/r290_20180206/1.jpg");



            mDbOpenHelper.insertColumn("미역초무침", "자른미역작은한줌, 오이1, 양파반개, 파프리카적당량, 간장3, 식초2, 매실청3, 레몬즙조금, 올리고당1, 다진마늘반큰술, 통깨","1. 자른 미역을 물에 불려 헹군 뒤, 물기를 짜서 볼에 담아줍니다.\n2. 오이, 파프리카, 양파를 먹기좋게 썰어 담아줍니다.\n3. 간장3, 식초2, 매실청3, 레몬즙조금, 올리고당1, 다진마늘반큰술, 통깨를 넣고 버무려줍니다.","1","https://recipe1.ezmember.co.kr/cache/recipe/2016/07/24/a8afa380d871276baed622915d52b0961.jpg");


            mDbOpenHelper.insertColumn("미역볶음밥", "밥 1인분, 불린 미역, 비엔나 소시지 15개, 가지 1개, 감자 2개, 당근 1/4개, 호박 1/4개, 양파 1개, 어린잎채소(생략가능) 조금, 계란 1개","1. 미역은 물에 불려준 후, 뜨거운 물에 약 30초만 담궜다 꺼내 다시 찬물에 씻어줍니다.\n2. 팬에 들기름을 두르고 재료를 모두 넣고 볶아줍니다.\n3. 양파가 투명해지면 밥과 굴소스 2큰술을 넣고 골고루 볶아주세요.","5","https://img1.daumcdn.net/thumb/R720x0.q80/?scode=mtistory2&fname=http%3A%2F%2Fcfile26.uf.tistory.com%2Fimage%2F27568A4056AE2F1B270E44");

        }

        home_btn = findViewById(R.id.home_btn);
        fav_btn = findViewById(R.id.fav_btn);
        myrep_btn = findViewById(R.id.myrep_btn);
        //editText = findViewById(R.id.editText); //검색창

        home_btn.setEnabled(false);
        fav_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(),FavoriteActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);
            }
        });
        myrep_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), MyRecipeListActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);

            }
        });
/*
        editText.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                Intent intent = new Intent(getApplicationContext(), SearchActivity.class);
                startActivity(intent);
            }
        });


        //데이터베이스 생성(파라메터 Context) 및 오픈
        mDbOpenHelper = new DBOpenHelper(this);
        try {
            mDbOpenHelper.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }
*/
        danbee_btn = findViewById(R.id.danbee_btn);
        //단비 챗봇 플로팅 버튼
        //클릭시 danbeeactivity로 전환
        danbee_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), DanbeeActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.right_to_left, R.anim.left_to_right);
            }
        });

        button_food = (ImageButton)findViewById(R.id.btn_food);
        autoFoodName = (AutoCompleteTextView)findViewById(R.id.autoFoodName);

        mDbOpenHelper = new DBOpenHelper(this);
        mDbOpenHelper.open();
        /*
        for(int i = 0 ; i <30; i++) {
            mDbOpenHelper.deleteRepColumn(i);
        }
*/
        //mDbOpenHelper.deleteRepColumn(30);

        /*
        for(int i = 0 ; i <15; i++) {
            mDbOpenHelper.insertColumn("떡볶이"+i,"떡","1.떡을 삶는다","1","empty image");
        }
        for(int i = 15 ; i <30; i++) {
            mDbOpenHelper.insertColumn("떡볶이"+i,"떡","1.떡을 삶는다","2","empty image");
        }
*/
        //mDbOpenHelper.insertColumn("떡볶이","떡","1.떡을 삶는다","1","https://recipe1.ezmember.co.kr/cache/recipe/2016/06/08/24c312f82313faaf1e4d5ef98761efcb1.jpg");
        //mDbOpenHelper.insertColumn("떡볶이","떡","1.떡을 삶는다","1","https://recipe1.ezmember.co.kr/cache/recipe/2016/06/08/24c312f82313faaf1e4d5ef98761efcb1.jpg");



        list = new ArrayList<String>();
        id_list = new ArrayList<String>();
        settingList(list, id_list);

        autoFoodName.setAdapter(new ArrayAdapter<String>(this,
                android.R.layout.simple_dropdown_item_1line, list));

        button_food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String foodName = autoFoodName.getText().toString();
                String foodId = "";
                for(int i =0; i< list.size(); i++){
                    if(list.get(i).equals(foodName)){
                        foodId = id_list.get(i);
                        break;
                    }
                }
                Log.d("testsearch",foodId);
                //검색창이 비지 않았으면
                if(!foodName.isEmpty()){
                    if(foodId.isEmpty()){
                        AlertDialog.Builder dialog = new AlertDialog.Builder(MainActivity.this);
                        dialog.setTitle("확인").setMessage("검색 결과가 없습니다").setCancelable(true).
                            setPositiveButton("확인", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialogInterface, int i) {
                                }
                            });
                        AlertDialog alert = dialog.create();
                        alert.show();
                    }
                    else {
                        Intent intent = new Intent(MainActivity.this, SearchContentActivity.class);
                        intent.putExtra("it_idRecipe", foodId);
                        startActivity(intent);
                    }
                }

            }
        });


        //Category
        button_cate1 = (ImageButton)findViewById(R.id.cate1_btn);
        button_cate2 = (ImageButton)findViewById(R.id.cate2_btn);
        button_cate3 = (ImageButton)findViewById(R.id.cate3_btn);
        button_cate4 = (ImageButton)findViewById(R.id.cate4_btn);
        button_cate5 = (ImageButton)findViewById(R.id.cate5_btn);


        button_cate1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CateListActivity.class);
                intent.putExtra("it_idRecipe", "1");
                startActivity(intent);

            }
        });

        button_cate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CateListActivity.class);
                intent.putExtra("it_idRecipe", "2");
                startActivity(intent);

            }
        });

        button_cate3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CateListActivity.class);
                intent.putExtra("it_idRecipe", "3");
                startActivity(intent);

            }
        });

        button_cate4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CateListActivity.class);
                intent.putExtra("it_idRecipe", "4");
                startActivity(intent);

            }
        });

        button_cate5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(getApplicationContext(), CateListActivity.class);
                intent.putExtra("it_idRecipe", "5");
                startActivity(intent);

            }
        });

    }

    protected void onPause(){
        super.onPause();
        overridePendingTransition(R.anim.right_to_left,R.anim.left_to_right);
    }

    private void settingList(List<String> list, List<String> id_list){
        mCursor = mDbOpenHelper.getRepAllColumns();

        while(mCursor.moveToNext()){

            food_id = mCursor.getString(mCursor.getColumnIndex("_id"));
            food_name = mCursor.getString(mCursor.getColumnIndex("recipe_name"));
            list.add(food_name);
            id_list.add(food_id);
            //cook_img = mCursor.getString(mCursor.getColumnIndex("recipe_image"));

            //Log.d("dbTest", cook_name);

        }
        mCursor.close();

    }
}
