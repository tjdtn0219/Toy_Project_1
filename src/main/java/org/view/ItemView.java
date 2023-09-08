package org.view;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

public interface ItemView<T, K> {

    public final Scanner sc = new Scanner(System.in);

    public final SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");

    public final SimpleDateFormat timeFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm");

    public T getDtoFromInput() throws ParseException;

    public void showDtoList(List<K> dtoList);

    public String chooseFileType();
}
