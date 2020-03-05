package com.pdf.convert;

import com.itextpdf.text.Document;
import com.itextpdf.text.pdf.*;

import java.io.ByteArrayOutputStream;
import java.io.FileOutputStream;
import java.util.*;

public class PdfFill {
    public static void main(String[] args) {
        // 模板路径
        String templatePath = "G:\\applicat-tip-paper-prod.pdf";
        // 生成的新文件路径
        String newPDFPath = "G:\\测试fill.pdf";
        PdfReader reader;
        FileOutputStream out;
        ByteArrayOutputStream bos;
        PdfStamper stamper;
        try {

            reader = new PdfReader(templatePath);
            bos = new ByteArrayOutputStream();
            stamper = new PdfStamper(reader, bos);
            AcroFields form = stamper.getAcroFields();

            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());//设置当前日期
            int year = calendar.get(Calendar.YEAR);//获取年份
            int month = calendar.get(Calendar.MONTH) + 1;//获取月份
            String monthStr = month < 10 ? "0" + month : month + "";
            int day = calendar.get(Calendar.DATE);//获取日
            Map<String, String> map = new HashMap<>();
            map.put("nowYear", String.valueOf(year));
            map.put("nowMonth", monthStr);
            map.put("nowDay", String.valueOf(day));
            map.put("stateMsg", "达到监管要求");
            map.put("years","2019");
            map.put("quarter","1");
            map.put("riskLevel","A");
            map.put("solvencyRate","100");

            // 设置中文字体
            BaseFont bf = BaseFont.createFont("STSong-Light", "UniGB-UCS2-H", BaseFont.NOT_EMBEDDED);
            ArrayList<BaseFont> fontList = new ArrayList<>(1);
            fontList.add(bf);
            form.setSubstitutionFonts(fontList);

            java.util.Iterator<String> it = form.getFields().keySet().iterator();
            while (it.hasNext()) {
                String name = it.next();
                form.setField(name, map.get(name));
            }
            //true代表生成的PDF文件不可编辑
            stamper.setFormFlattening(true);
            stamper.close();

            Document doc = new Document();
            out = new FileOutputStream(newPDFPath);
            PdfCopy copy = new PdfCopy(doc, out);
            doc.open();
            int pageNum = reader.getNumberOfPages();
            System.out.println("pageNum = " + pageNum);
            for(int i = 1;i<=pageNum;i++){
                PdfImportedPage importPage = copy.getImportedPage(new PdfReader(bos.toByteArray()), i);
                copy.addPage(importPage);
            }
            doc.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
