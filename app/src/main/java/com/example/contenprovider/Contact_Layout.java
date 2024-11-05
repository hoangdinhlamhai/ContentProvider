package com.example.contenprovider;

import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

import Model.Contact;

public class Contact_Layout extends AppCompatActivity {

    private final int REQUEST_CONTACTS_ASK_PERMISSIONS = 1001;

    ListView Contactlv;
    ArrayList<Contact> listContact;
    ArrayAdapter<Contact> ContactAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_contact);
        addControls();

        if (checkSelfPermission(android.Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{android.Manifest.permission.READ_CONTACTS}, REQUEST_CONTACTS_ASK_PERMISSIONS);
        } else {
            showAllContactFromDevice();
        }
    }

    private void showAllContactFromDevice() {
        Uri uri = ContactsContract.CommonDataKinds.Phone.CONTENT_URI;
        Cursor cursor = null;

        try {
            cursor = getContentResolver().query(uri, null, null, null, null);
            if (cursor != null) {
                listContact.clear();
                while (cursor.moveToNext()) {
                    String tenCotName = ContactsContract.Contacts.DISPLAY_NAME;
                    String tenCotPhone = ContactsContract.CommonDataKinds.Phone.NUMBER;

                    int viTriCotName = cursor.getColumnIndex(tenCotName);
                    int viTriCotPhone = cursor.getColumnIndex(tenCotPhone);

                    String name = cursor.getString(viTriCotName);
                    String phone = cursor.getString(viTriCotPhone);

                    Contact contact = new Contact(name, phone);
                    listContact.add(contact);
                }
                ContactAdapter.notifyDataSetChanged();
            }
        } finally {
            if (cursor != null) {
                cursor.close(); // Đảm bảo đóng cursor
            }
        }
    }

    private void addControls() {
        Contactlv = findViewById(R.id.lvDanhBa);
        listContact = new ArrayList<>();
        ContactAdapter = new ArrayAdapter<>(Contact_Layout.this, android.R.layout.simple_list_item_1, listContact);
        Contactlv.setAdapter(ContactAdapter);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CONTACTS_ASK_PERMISSIONS) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                showAllContactFromDevice();
            } else {
                // Thông báo cho người dùng rằng quyền đã bị từ chối
            }
        }
    }
}
