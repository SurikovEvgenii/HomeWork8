package org.surikov.homework8;

import java.io.*;
import java.util.Arrays;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

@WebServlet(name = "textServlet", value = "/text-servlet")

public class TextServlet extends HttpServlet {

    String path = "C:\\Users\\Пользователь\\IdeaProjects\\HomeWork8\\src\\main\\resources\\text.txt";

    public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
            String seqSymbol = String.valueOf(getCountSymbolArray(path));
            Cookie seqSymbolCookie = new Cookie("seqSymbol", seqSymbol);
            response.addCookie(seqSymbolCookie);
            request.setAttribute("seqSymbolAttr", seqSymbol);
                try {
                    request.getRequestDispatcher("index.jsp").forward(request,response);
                } catch (ServletException e) {
                    throw new RuntimeException(e);
                }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String story = req.getParameter("story");

            try (OutputStreamWriter outputStreamWriter = new FileWriter(path)) {
                outputStreamWriter.write(story);
            } catch (IOException ex){
                ex.getCause();
            }

        String seqSymbol = String.valueOf(getCountSymbolArray(path));
        Cookie seqSymbolCookie = new Cookie("seqSymbol", seqSymbol);
        resp.addCookie(seqSymbolCookie);
        req.setAttribute("seqSymbolAttr", seqSymbol);

            try {
                req.getRequestDispatcher("index.jsp").forward(req,resp);
            } catch (ServletException e) {
                throw new RuntimeException(e);
            }

    }

    public int getCountSymbolArray(String path) {

        int[] symbolSequence = new int[30];
        char[] buffer = new char[100];
        int counter = 0;
        int lenCounter = 0;

        try(InputStreamReader reader = new FileReader(new File(path))) {
            //Читаем пока не будут считаны все символы в буффер.
            while(reader.read(buffer)!=-1){

                for(int i=0; i<buffer.length-1; i++){
                    for(int j=32; j<65; j++){
                        //Проверяем символ
                        if(buffer[i]==j){
                            lenCounter++;
                        }
                        /*
                        Так как читаем слева направо,то проверяем, является ли следующий символ буквой.
                        Если да, то прекращаем считать последовательность и записываем в массив,
                        где содержатся 30 штук последних считываемых последовательностей.

                        */
                        if(buffer[i+1]>64 && buffer[i]==j){
                            symbolSequence[counter] = lenCounter;
                            counter++;
                            lenCounter = 0;
                            break;
                        }
                    }

                    /*
                    Чтобы массив не переполнялся, обнуляем его и в последнюю ячейку
                    записываем максимальное значение.
                    Обновляем его в том числе.
                     */
                    if(counter==28){
                        symbolSequence[29] = Arrays.stream(symbolSequence).max().getAsInt();
                        counter = 0;
                    }

                }
            }
        } catch (IOException ex){
            ex.getCause();
        }
        //Чтобы не писать отдельный метод - использую Stream вместо отделльного метода для возвращения значения.

        return Arrays.stream(symbolSequence).max().getAsInt();

    }
}
