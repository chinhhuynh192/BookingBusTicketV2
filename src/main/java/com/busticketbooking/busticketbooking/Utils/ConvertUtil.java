package com.busticketbooking.busticketbooking.Utils;

import com.busticketbooking.busticketbooking.models.Booking;
import org.apache.commons.codec.digest.DigestUtils;

import javax.servlet.http.HttpSession;
import java.io.Serializable;
import java.sql.Date;
import java.sql.Time;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.stream.Collectors;

public class ConvertUtil {
    public static int convert(String str) throws NumberFormatException {
        return Integer.parseInt(str);
    }

    public static Date getDateNow(){
        java.util.Date dateNow = new java.util.Date();
        return new java.sql.Date(dateNow.getTime());
    }
    public static String formatTime(Time sqlTime) {
        // Convert java.sql.Time to LocalTime
        LocalTime localTime = sqlTime.toLocalTime();

        // Format LocalTime to display only hours and minutes
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return localTime.format(formatter);
    }

    public static Date convertStringToDate(String dateStr) throws ParseException {
        java.util.Date date = new SimpleDateFormat("yyyy-MM-dd").parse(dateStr);
        return new java.sql.Date(date.getTime());
    }
    public static String formatDate(Date sqlDate) {
        // Convert java.sql.Date to LocalDate
        LocalDate localDate = sqlDate.toLocalDate();

        // Format LocalDate to display in 'DD-MM-YYYY'
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return localDate.format(formatter);
    }


    public static List<String> castStringListToList(String string){
        return Arrays.asList(string.split(",", -1));
    }

    public static boolean isNullOrEmpty(String str){
        boolean isEmpty = str == null || str.trim().isEmpty();
        if (isEmpty) {
            return true;
        }
        return false;
    }

    public static String floatToHourAndMinutes(float value) {
        int hours = (int) value;
        int minutes = Math.round((value - hours) * 60);
        return hours + " giờ " + minutes + " phút";
    }
    public static List<Integer> getAllSeatNumbers(List<Booking> bookings) {
        if(bookings.size() == 0){
            return Collections.emptyList();
        }
        return bookings.stream()
                .flatMap(booking -> Arrays.stream(booking.getSeatNumber().split(",")))
                .map(Integer::parseInt)
                .distinct()
                .collect(Collectors.toList());
    }

    public static String formatToMoney(float value) {
        // Define the format pattern
        DecimalFormat formatter = new DecimalFormat("#,###.###");

        // Use a dot as a grouping separator instead of a comma
        DecimalFormatSymbols symbols = formatter.getDecimalFormatSymbols();
        symbols.setGroupingSeparator('.');
        formatter.setDecimalFormatSymbols(symbols);

        // Format the value and append 'đ' symbol
        return formatter.format(value) + "đ";
    }
    public static Time calculateEndTime(Time startTime, float duration) {
        // Convert Time to milliseconds
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        long startTimeMillis = (calendar.get(Calendar.HOUR_OF_DAY) * 60L + calendar.get(Calendar.MINUTE)) * 60 * 1000;

        // Convert duration to milliseconds
        int hours = (int) duration;
        int minutes = Math.round((duration - hours) * 60);
        long durationMillis = ((hours * 60L) + minutes) * 60 * 1000;

        // Calculate end time in milliseconds
        long endTimeMillis = startTimeMillis + durationMillis;

        // Convert back to Time
        return new Time(endTimeMillis - java.util.TimeZone.getDefault().getRawOffset()); // Adjust for timezone offset
    }
    public static int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    public static String calculateAmount(float price, float discount){
        float amount = price * (1- discount);
        return formatToMoney(amount);
    }
    public static <T extends Serializable> T getAttr(String name, HttpSession sess) {
        @SuppressWarnings("unchecked")
        AttrWrapper<T> attr = (AttrWrapper<T>) sess.getAttribute(name);
        if (attr == null)
            return null;
        if (attr.isValid())
            return attr.value; // Attribute is valid, you can use it

        // Attribute is invalid, timed out, remove it
        sess.removeAttribute(name);
        return null;
    }

    public static String getHashCode(){
        Random theRandom = new Random();
        theRandom.nextInt(999999);
        return DigestUtils.md5Hex("" +	theRandom);
    }


    public static String htmlTicketTemplate(Booking booking, String seatNumber, float money){
        return String.format(
                "<!DOCTYPE html>\n" +
                "<html lang=\"vi\" style=\"box-sizing: border-box;\">\n" +
                "<head style=\"box-sizing: border-box;\">\n" +
                "    <meta charset=\"UTF-8\" style=\"box-sizing: border-box;\">\n" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\" style=\"box-sizing: border-box;\">\n" +
                "    <title style=\"box-sizing: border-box;\">Vé Xe Buýt DN</title>\n" +
                "    \n" +
                "    \n" +
                "</head>\n" +
                "<body style=\"box-sizing: border-box;margin: 0;font-family: var(--bs-body-font-family);font-size: var(--bs-body-font-size);font-weight: var(--bs-body-font-weight);line-height: var(--bs-body-line-height);color: var(--bs-body-color);text-align: var(--bs-body-text-align);background-color: var(--bs-body-bg);-webkit-text-size-adjust: 100%%;-webkit-tap-highlight-color: transparent;\">\n" +
                "    \n" +
                "\n" +
                "<div class=\"container\" style=\"box-sizing: border-box;width: 75%%;padding-right: var(--bs-gutter-x,.75rem);padding-left: var(--bs-gutter-x,.75rem);margin-right: auto;margin-left: auto;\">\n" +
                "    <div class=\"ticket\" style=\"box-sizing: border-box;border: 1px solid #ddd;padding: 15px;margin: 20px auto;max-width: 600px;background-color: #f9f9f9;\">\n" +
                "        <div class=\"ticket-header\" style=\"box-sizing: border-box;text-align: center;margin-bottom: 20px;\">\n" +
                "            <h1 style=\"box-sizing: border-box;margin-top: 0;margin-bottom: .5rem;font-weight: 500;line-height: 1.2;font-size: calc(1.375rem + 1.5vw);\">Vé Xe</h1>\n" +
                "        </div>\n" +
                "        \n" +
                "        <div class=\"row details-row\" style=\"box-sizing: border-box;--bs-gutter-x: 1.5rem;--bs-gutter-y: 0;display: flex;flex-wrap: wrap;margin-top: calc(-1 * var(--bs-gutter-y));margin-right: calc(-.5 * var(--bs-gutter-x));margin-left: calc(-.5 * var(--bs-gutter-x));margin-bottom: 10px;\">\n" +
                "            <div class=\"col\" style=\"box-sizing: border-box;flex-shrink: 0;width: 100%%;max-width: 100%%;padding-right: calc(var(--bs-gutter-x) * .5);padding-left: calc(var(--bs-gutter-x) * .5);margin-top: var(--bs-gutter-y);flex: 1 0 0%%;\">\n" +
                "                <strong style=\"box-sizing: border-box;font-weight: bolder;\">Từ:</strong> <span id=\"from\" style=\"box-sizing: border-box;\">%s</span>\n" +
                "            </div>\n" +
                "            <div class=\"col\" style=\"box-sizing: border-box;flex-shrink: 0;width: 100%%;max-width: 100%%;padding-right: calc(var(--bs-gutter-x) * .5);padding-left: calc(var(--bs-gutter-x) * .5);margin-top: var(--bs-gutter-y);flex: 1 0 0%%;\">\n" +
                "                <strong style=\"box-sizing: border-box;font-weight: bolder;\">Đến:</strong> <span id=\"to\" style=\"box-sizing: border-box;\">%s</span>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"row details-row\" style=\"box-sizing: border-box;--bs-gutter-x: 1.5rem;--bs-gutter-y: 0;display: flex;flex-wrap: wrap;margin-top: calc(-1 * var(--bs-gutter-y));margin-right: calc(-.5 * var(--bs-gutter-x));margin-left: calc(-.5 * var(--bs-gutter-x));margin-bottom: 10px;\">\n" +
                "            <div class=\"col\" style=\"box-sizing: border-box;flex-shrink: 0;width: 100%%;max-width: 100%%;padding-right: calc(var(--bs-gutter-x) * .5);padding-left: calc(var(--bs-gutter-x) * .5);margin-top: var(--bs-gutter-y);flex: 1 0 0%%;\">\n" +
                "                <strong style=\"box-sizing: border-box;font-weight: bolder;\">Ngày:</strong> <span id=\"date\" style=\"box-sizing: border-box;\">%s</span>\n" +
                "            </div>\n" +
                "            <div class=\"col\" style=\"box-sizing: border-box;flex-shrink: 0;width: 100%%;max-width: 100%%;padding-right: calc(var(--bs-gutter-x) * .5);padding-left: calc(var(--bs-gutter-x) * .5);margin-top: var(--bs-gutter-y);flex: 1 0 0%%;\">\n" +
                "                <strong style=\"box-sizing: border-box;font-weight: bolder;\">Giờ:</strong> <span id=\"time\" style=\"box-sizing: border-box;\">%s</span>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"row details-row\" style=\"box-sizing: border-box;--bs-gutter-x: 1.5rem;--bs-gutter-y: 0;display: flex;flex-wrap: wrap;margin-top: calc(-1 * var(--bs-gutter-y));margin-right: calc(-.5 * var(--bs-gutter-x));margin-left: calc(-.5 * var(--bs-gutter-x));margin-bottom: 10px;\">\n" +
                "            <div class=\"col\" style=\"box-sizing: border-box;flex-shrink: 0;width: 100%%;max-width: 100%%;padding-right: calc(var(--bs-gutter-x) * .5);padding-left: calc(var(--bs-gutter-x) * .5);margin-top: var(--bs-gutter-y);flex: 1 0 0%%;\">\n" +
                "                <strong style=\"box-sizing: border-box;font-weight: bolder;\">Số Ghế:</strong> <span id=\"seat-number\" style=\"box-sizing: border-box;\">%s</span>\n" +
                "            </div>\n" +
                "            <div class=\"col\" style=\"box-sizing: border-box;flex-shrink: 0;width: 100%%;max-width: 100%%;padding-right: calc(var(--bs-gutter-x) * .5);padding-left: calc(var(--bs-gutter-x) * .5);margin-top: var(--bs-gutter-y);flex: 1 0 0%%;\">\n" +
                "                <strong style=\"box-sizing: border-box;font-weight: bolder;\">Số Vé:</strong> <span id=\"ticket-number\" style=\"box-sizing: border-box;\">%s</span>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"row details-row\" style=\"box-sizing: border-box;--bs-gutter-x: 1.5rem;--bs-gutter-y: 0;display: flex;flex-wrap: wrap;margin-top: calc(-1 * var(--bs-gutter-y));margin-right: calc(-.5 * var(--bs-gutter-x));margin-left: calc(-.5 * var(--bs-gutter-x));margin-bottom: 10px;\">\n" +
                "            <div class=\"col-6\" style=\"box-sizing: border-box;flex-shrink: 0;width: 50%%;max-width: 100%%;padding-right: calc(var(--bs-gutter-x) * .5);padding-left: calc(var(--bs-gutter-x) * .5);margin-top: var(--bs-gutter-y);flex: 0 0 auto;\">\n" +
                "                <strong style=\"box-sizing: border-box;font-weight: bolder;\">Tên Hành Khách:</strong> <span id=\"passenger-name\" style=\"box-sizing: border-box;\">%s</span>\n" +
                "            </div>\n" +
                "            <div class=\"col-6\" style=\"box-sizing: border-box;flex-shrink: 0;width: 50%%;max-width: 100%%;padding-right: calc(var(--bs-gutter-x) * .5);padding-left: calc(var(--bs-gutter-x) * .5);margin-top: var(--bs-gutter-y);flex: 0 0 auto;\">\n" +
                "                <strong style=\"box-sizing: border-box;font-weight: bolder;\">Điện Thoại:</strong> <span id=\"phone\" style=\"box-sizing: border-box;\">%s</span>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"row details-row\" style=\"box-sizing: border-box;--bs-gutter-x: 1.5rem;--bs-gutter-y: 0;display: flex;flex-wrap: wrap;margin-top: calc(-1 * var(--bs-gutter-y));margin-right: calc(-.5 * var(--bs-gutter-x));margin-left: calc(-.5 * var(--bs-gutter-x));margin-bottom: 10px;\">\n" +
                "            <div class=\"col-6\" style=\"box-sizing: border-box;flex-shrink: 0;width: 50%%;max-width: 100%%;padding-right: calc(var(--bs-gutter-x) * .5);padding-left: calc(var(--bs-gutter-x) * .5);margin-top: var(--bs-gutter-y);flex: 0 0 auto;\">\n" +
                "                <strong style=\"box-sizing: border-box;font-weight: bolder;\">Email:</strong> <span id=\"email\" style=\"box-sizing: border-box;\">%s</span>\n" +
                "            </div>\n" +
                "            <div class=\"col-6\" style=\"box-sizing: border-box;flex-shrink: 0;width: 50%%;max-width: 100%%;padding-right: calc(var(--bs-gutter-x) * .5);padding-left: calc(var(--bs-gutter-x) * .5);margin-top: var(--bs-gutter-y);flex: 0 0 auto;\">\n" +
                "                <strong style=\"box-sizing: border-box;font-weight: bolder;\">Giá:</strong> <span id=\"price\" style=\"box-sizing: border-box;\">%s</span>\n" +
                "            </div>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"qr-code\" style=\"box-sizing: border-box;text-align: center;margin-top: 15px;\">\n" +
                "            <img src=\"https://via.placeholder.com/150\" alt=\"Mã QR\" style=\"box-sizing: border-box;vertical-align: middle;\">\n" +
                "            <p style=\"box-sizing: border-box;margin-top: 0;margin-bottom: 1rem;\">Quét để xem thông tin chi tiết</p>\n" +
                "        </div>\n" +
                "\n" +
                "        <div class=\"ticket-footer text-center mt-4\" style=\"box-sizing: border-box;margin-top: 1.5rem!important;text-align: center!important;\">\n" +
                "            <p style=\"box-sizing: border-box;margin-top: 0;margin-bottom: 1rem;\">Cảm ơn bạn đã chọn dịch vụ của DN BUS!</p>\n" +
                "        </div>\n" +
                "    </div>\n" +
                "</div>\n" +
                "</body>\n" +
                "</html>", booking.getTrip().getRoute().getOrigin(), booking.getTrip().getRoute().getDestination(), formatDate(booking.getTrip().getDate()), formatTime(booking.getTrip().getTime()), seatNumber, booking.getBookingId(), booking.getUser().getName(), booking.getUser().getPhone(), booking.getUser().getEmail(), formatToMoney(money) );
    }
}
