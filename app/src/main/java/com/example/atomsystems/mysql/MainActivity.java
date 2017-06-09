package com.example.atomsystems.mysql;

import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DataBaseHelper dataBaseHelper;
    EditText productName,quantity,price,id;
    Button addDetails,viewDetails,DeleteData,UpdateData,DeleteAllData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        dataBaseHelper = new DataBaseHelper(this);

        productName = (EditText)findViewById(R.id.productname_edittext);
        quantity = (EditText)findViewById(R.id.quantity_edittext);
        price = (EditText)findViewById(R.id.price_edittext);
        price = (EditText)findViewById(R.id.price_edittext);
        id = (EditText)findViewById(R.id.id_edittext);
        addDetails = (Button)findViewById(R.id.add_button);
        viewDetails = (Button)findViewById(R.id.View_button);
        DeleteData = (Button)findViewById(R.id.delete_button);
        UpdateData = (Button)findViewById(R.id.update_button);
        DeleteAllData = (Button)findViewById(R.id.deleteall_button);
        AddDetails();
        ViewAllData();
        DeleteData();
        UpdateData();
        DeleteAll();
    }
    public void AddDetails(){

        addDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                boolean IsEntry =  dataBaseHelper.IsertDeleteSpace(id.getText().toString(),productName.getText().toString(),
                        quantity.getText().toString(), price.getText().toString());

                if (IsEntry) {
                    Toast.makeText(MainActivity.this, "Data Entry With ID", Toast.LENGTH_SHORT).show();
                }
                else{

                    dataBaseHelper.insertData(productName.getText().toString(),
                            quantity.getText().toString(), price.getText().toString());
                    Toast.makeText(MainActivity.this,"Data Entry With Continue ID",Toast.LENGTH_SHORT).show();
                }
                productName.setText("");
                quantity.setText("");
                price.setText("");
                id.setText("");
            }


        });

    }

    public void UpdateData(){
        UpdateData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                boolean isupdate = dataBaseHelper.UpdateData(id.getText().toString(),productName.getText().toString(),
                        quantity.getText().toString(),price.getText().toString());

                if (isupdate) {
                    Toast.makeText(MainActivity.this, "Data Update Successful", Toast.LENGTH_SHORT).show();
                }
                else {
                    Toast.makeText(MainActivity.this, "Data Not Update", Toast.LENGTH_SHORT).show();
                }
                productName.setText("");
                quantity.setText("");
                price.setText("");
                id.setText("");
            }
        });

    }
    public void ViewAllData(){
        viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Cursor cursorresult = dataBaseHelper.getAllData();
                if (cursorresult.getCount() == 0){

                    showmsg("Please add a product details!!!","Nothing Found");

                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while (cursorresult.moveToNext()){

                    buffer.append("Id :"+cursorresult.getString(0)+"\n");
                    buffer.append("Name :"+cursorresult.getString(1)+"\n");
                    buffer.append("Quantity :"+cursorresult.getString(2)+"\n");
                    buffer.append("Price :"+cursorresult.getString(3)+"\n\n\n");
                }

                showmsg("Purchase Details",buffer.toString());

                productName.setText("");
                quantity.setText("");
                price.setText("");
                id.setText("");
            }
        });

    }
    public void DeleteData() {
        DeleteData.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleterows = dataBaseHelper.DeleteData(id.getText().toString());
                if (deleterows > 0)
                    Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_SHORT).show();
                else
                    Toast.makeText(MainActivity.this, "Data Not Found", Toast.LENGTH_SHORT).show();

                productName.setText("");
                quantity.setText("");
                price.setText("");
                id.setText("");
            }
        });
    }

        public void DeleteAll(){
            DeleteAllData.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    dataBaseHelper.DeleteAll();
                    Toast.makeText(MainActivity.this, "All Data Deleted", Toast.LENGTH_SHORT).show();
                }
            });


    }
    public void showmsg(String title,String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
