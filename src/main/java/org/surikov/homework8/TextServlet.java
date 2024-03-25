package org.surikov.homework8;

import java.io.*;

import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "textServlet", value = "/textw-servlet")
public class TextServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String path = "C:\\Users\\Пользователь\\IdeaProjects\\HomeWork8\\src\\main\\resources\\text.txt";
        getCountSymbolArray(path);
    }

    public void getCountSymbolArray(String path) {

        byte[] buffer = null;

        try(InputStream inputStream = new FileInputStream(new File(path))) {
            buffer = inputStream.readAllBytes();
            System.out.println(buffer);
        } catch (IOException ex){
            ex.getCause();
        }
    }

}
