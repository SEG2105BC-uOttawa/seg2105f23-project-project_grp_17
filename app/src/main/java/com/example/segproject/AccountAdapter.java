//package com.example.segproject;
//
//import android.annotation.SuppressLint;
//import android.app.Activity;
//import android.content.Context;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ArrayAdapter;
//import android.widget.TextView;
//
//import androidx.annotation.NonNull;
//
//import java.util.List;
//
//public class AccountAdapter extends ArrayAdapter<Account> {
//    private Activity context;
//    List<Account> accounts;
//
//    public AccountAdapter(@NonNull Activity context, List<Account> accounts) {
//        super(context, R.layout.activity_account_list, accounts);
//        this.context = context;
//        this.accounts = accounts;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater inflater = context.getLayoutInflater();
//        View listViewItem = inflater.inflate(R.layout.activity_account_list, null, true);
//
//        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textAccountUser = (TextView) listViewItem.findViewById(R.id.textAccountUser);
//        @SuppressLint({"MissingInflatedId", "LocalSuppress"}) TextView textAccountEmail = (TextView) listViewItem.findViewById(R.id.textAccountEmail);
//
//        Account account = accounts.get(position);
//        textAccountUser.setText(account.getEmail());
//        textAccountEmail.setText(String.valueOf(account.getUsername()));
//        return listViewItem;
//    }
//}