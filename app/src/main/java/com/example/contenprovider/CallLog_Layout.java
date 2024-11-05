package com.example.contenprovider;

import android.Manifest;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.CallLog;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CallLog_Layout extends AppCompatActivity {

    private ListView lvLichSuCuocGoi;
    private List<String> callLogList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_calllog);

        lvLichSuCuocGoi = findViewById(R.id.lvLichSuCuocGoi);

        // Check for permission
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CALL_LOG) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.READ_CALL_LOG}, 1);
        } else {
            getCallLogs();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 && grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            getCallLogs();
        }
    }

    private void getCallLogs() {
        callLogList = new ArrayList<>();
        Cursor cursor = getContentResolver().query(CallLog.Calls.CONTENT_URI, null, null, null, CallLog.Calls.DATE + " DESC");

        if (cursor != null) {
            int numberIndex = cursor.getColumnIndex(CallLog.Calls.NUMBER);
            int typeIndex = cursor.getColumnIndex(CallLog.Calls.TYPE);
            int dateIndex = cursor.getColumnIndex(CallLog.Calls.DATE);
            int durationIndex = cursor.getColumnIndex(CallLog.Calls.DURATION);

            while (cursor.moveToNext()) {
                String number = cursor.getString(numberIndex);
                String type;
                switch (Integer.parseInt(cursor.getString(typeIndex))) {
                    case CallLog.Calls.INCOMING_TYPE:
                        type = "Incoming";
                        break;
                    case CallLog.Calls.OUTGOING_TYPE:
                        type = "Outgoing";
                        break;
                    case CallLog.Calls.MISSED_TYPE:
                        type = "Missed";
                        break;
                    case CallLog.Calls.REJECTED_TYPE:
                        type = "Rejected";
                        break;
                    default:
                        type = "Unknown";
                        break;
                }
                String date = new Date(Long.parseLong(cursor.getString(dateIndex))).toString();
                String duration = cursor.getString(durationIndex);

                String logEntry = "Number: " + number + "\nType: " + type + "\nDate: " + date + "\nDuration: " + duration + " seconds";
                callLogList.add(logEntry);
            }
            cursor.close();
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, callLogList);
        lvLichSuCuocGoi.setAdapter(adapter);
    }
}
