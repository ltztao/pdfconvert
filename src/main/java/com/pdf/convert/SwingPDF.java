package com.pdf.convert;

import com.aspose.pdf.Document;
import com.aspose.pdf.License;
import com.aspose.pdf.SaveFormat;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class SwingPDF {

    public static void main(String[] args) {
        // 创建 JFrame 实例
        final JFrame frame = new JFrame("PDF转WORD");
        // 禁止最大化
        frame.setResizable(false);
        // Setting the width and height of frame
        frame.setSize(500, 400);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationRelativeTo(null);
        //在屏幕中居中显示
        /* 创建面板，这个类似于 HTML 的 div 标签
         * 我们可以创建多个面板并在 JFrame 中指定位置
         * 面板中我们可以添加文本字段，按钮及其他组件。
         */
        JPanel panel = new JPanel();
        // 添加面板
        frame.add(panel);
        /* 布局部分我们这边不多做介绍
         * 这边设置布局为 null
         */
        panel.setLayout(null);

        // 可滚动面板
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(0,50,500,200);
//        frame.getContentPane().add(scrollPane, BorderLayout.SOUTH);
        panel.add(scrollPane);
        final JTextArea textArea = new JTextArea();
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setEditable(false);
        scrollPane.setViewportView(textArea);

        JButton button = new JButton("选择文件");
        button.setBounds(380, 12, 100, 25);
        // 监听button的选择路径
        final List<File> fileList = new ArrayList<>();
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // 显示打开的文件对话框
                JFileChooser jfc = new JFileChooser();
                //在弹出对话框之前，创建文件类型的过滤器

                FileNameExtensionFilter filter=
                        new FileNameExtensionFilter("仅能打开 *.pdf", "pdf");
                //为chooser应用该filter
                jfc.setFileFilter(filter);

                jfc.setMultiSelectionEnabled(true);
                //设定当前可选择的文件类型，设定为能选择文件和文件夹
                //如果没有设定，默认为 FILES_ONLY，即只能选择文件
                jfc.setFileSelectionMode(JFileChooser.FILES_ONLY);
                jfc.showSaveDialog(frame);
                try {
                    // 使用文件类获取选择器选择的文件
                    File[] files =jfc.getSelectedFiles();
                    textArea.setText(null);
                    for(File file:files) {
                        fileList.add(file);
                        textArea.append(file.getName());
                        textArea.append("\r\n");
                    }
                } catch (Exception ex) {
                    textArea.setText(null);
                    textArea.append(String.format("工具异常：%s",ex.getMessage()));
                }
            }
        });
        panel.add(button);

        // JLabel 输出目录
        JLabel passwordLabel = new JLabel("输出目录:");
        passwordLabel.setBounds(10,270,80,25);
        panel.add(passwordLabel);
        // 文本输出目录
        final JTextField  outPathText = new JTextField ();
        outPathText.setBounds(70,270,410,25);
        outPathText.setText(System.getProperty("user.dir"));
        panel.add(outPathText);

        // 提示区域
        final JTextArea outTip = new JTextArea();
        outTip.setBounds(0,348,500,20);
        outTip.setText("  就绪...");
        outTip.setEditable(false);
        outTip.setForeground(Color.red);
        panel.add(outTip);

        // 转化 button
        JButton CButton = new JButton("转化");
        CButton.setBounds(380, 310, 100, 25);
        CButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try{
                    // 导入证书
                    loadLicense();
                    String outPath = outPathText.getText();
                    if (!outPath.endsWith("\\") && !outPath.endsWith("/")) {
                        outPath = outPath + File.separator;
                    }
                    if (fileList.isEmpty()) {
                        JOptionPane.showConfirmDialog(frame, "请选择pdf文件", "提示信息", JOptionPane.CLOSED_OPTION);
                        return;
                    }
                    for(File file:fileList){
                        InputStream is = new FileInputStream(file);
                        // load the file to be converted
                        Document pdf = new Document(is);
                        String filePath = outPath+file.getName().replace("pdf","docx");
                        // save in different formats
                        pdf.save(filePath, SaveFormat.DocX);
                    }
                    JOptionPane.showConfirmDialog(frame, "pdf转化成功", "提示信息", JOptionPane.CLOSED_OPTION);
                }catch (Exception ex){
                    outTip.setText(String.format(" 转化异常：%s",ex.getMessage()));
                }
            }
        });
        panel.add(CButton);
        // 设置界面可见
        frame.setVisible(true);
    }

    public static void loadLicense() throws Exception{
        InputStream is = SwingPDF.class.getResourceAsStream("/license.xml");
        License aposeLic = new License();
        aposeLic.setLicense(is);
    }

}