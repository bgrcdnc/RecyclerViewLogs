package com.bugracdnc.sqlitelearning;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.Locale;

public class Logs {
    private static Logs logs;
    private final ArrayList<String> logsBuffer;
    private final LogsAdapter logsAdapter;

    private Logs() {
        logsBuffer = new ArrayList<String>();
        logsAdapter = new LogsAdapter(this);
    }

    public static Logs getInstance() {
        if (logs == null) logs = new Logs();
        return logs;
    }

    @NonNull
    public LogsAdapter getAdapter() {
        assert logsAdapter != null;
        return logsAdapter;
    }

    public int size() {
        return logsBuffer.size();
    }

    public String get(int i) {
        return logsBuffer.get(i);
    }

    public void print(Object o) {
        logsBuffer.add(o.toString());
    }

    public void printf(String format, Object... args) {
        print(String.format(Locale.getDefault(), format, args));
    }

    public void printf(Locale locale, String format, Object... args) {
        print(String.format(locale, format, args));
    }
}
