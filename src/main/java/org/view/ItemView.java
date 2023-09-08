package org.view;

import org.constant.FileType;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Scanner;

import static org.constant.FormatPattern.*;

public interface ItemView<T, K> {

    public final Scanner sc = new Scanner(System.in);

    public final SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);

    public final SimpleDateFormat timeFormat = new SimpleDateFormat(TIME_FORMAT);

    public T getDtoFromInput() throws ParseException;

    public void showDtoList(List<K> dtoList);

    public void showSaveResult(K dto);

    public FileType chooseFileType();
}
