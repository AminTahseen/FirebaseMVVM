package com.example.firebasemvvm.ui;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.firebasemvvm.R;
import com.example.firebasemvvm.adapters.MainAdapter;
import com.example.firebasemvvm.models.User;
import com.example.firebasemvvm.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private MainAdapter adapter;
    Button addObj,refreshList;
    private UserViewModel viewModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        addObj=findViewById(R.id.AddObj);
        refreshList=findViewById(R.id.Refresh);
        recyclerView=findViewById(R.id.rview);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        viewModel= ViewModelProviders.of(MainActivity.this).get(UserViewModel.class);
        viewModel.init(MainActivity.this);
        adapter=new MainAdapter();
        adapter.setList(viewModel.getUserData().getValue());
        recyclerView.setAdapter(adapter);

        addObj.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Add_UserDialog(MainActivity.this);
            }
        });
        refreshList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                adapter.setList(viewModel.getUserData().getValue());
                recyclerView.setAdapter(adapter);
            }
        });
    }

    public void Add_UserDialog(Context context)
    {
        AlertDialog.Builder builder=new AlertDialog.Builder(this);
        View dialogView=getLayoutInflater().inflate(R.layout.input_dialog, null);
        Button add_user=dialogView.findViewById(R.id.btn_add);
        final EditText editName=dialogView.findViewById(R.id.name);
        final EditText editOccup=dialogView.findViewById(R.id.occup);

        add_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username=editName.getText().toString();
                String userOccup=editOccup.getText().toString();

                User user=new User(username,userOccup);
                viewModel.Add_User(user);
                adapter.setList(viewModel.getUserData().getValue());
                adapter.notifyDataSetChanged();
            }
        });
        builder.setView(dialogView);
        builder.setTitle("Add New User");
        AlertDialog dialog=builder.create();
        dialog.show();


    }
}