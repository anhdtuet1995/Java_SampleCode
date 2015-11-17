/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dictionary.java;

/**
 *
 * @author Anh
 */


import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

public class Dictionary extends JFrame implements ActionListener {
    
    private JButton btDich, btAnhViet, btVietAnh, btThem, btSua;
    JTextField tfNhap, tfSuaAnh, tfSuaViet;
    JTextField tfThemTuAnh, tfThemTuViet;
    
     int flag =0; // anh-viet
    private JList<String> list;
    JTextArea tx;
    JPanel p4;
    Translate translate;
    private DefaultListModel<String> listModel;
    Dictionary() throws IOException{
        this.translate = new Translate();
        
        setSize(750, 700);
	setTitle("Từ điển A-V");
	setLocationRelativeTo(null);
	setResizable(false);
	setDefaultCloseOperation(EXIT_ON_CLOSE);
        gui();
    }


    
    public static void main(String[] args) throws IOException {
        
        new Dictionary().setVisible(true);
    }
    
        
    public void gui(){
        
        JPanel p_main = new JPanel();
		
	JPanel p1 = new JPanel();
	JLabel lbNhap = new JLabel("Nhập:");
	tfNhap = new JTextField(15);
	btDich = new JButton("Dịch");
        p1.setBorder(BorderFactory.createTitledBorder("Tìm từ"));
		p1.add(lbNhap, new BorderLayout().SOUTH);
		p1.add(tfNhap, new BorderLayout().SOUTH);
		p1.add(btDich, new BorderLayout().SOUTH);
		p1.setPreferredSize(new Dimension(300, 70));
                
        p_main.add(p1);
        
        JPanel p2 = new JPanel();
		btAnhViet = new JButton("Anh - Việt");
		btVietAnh = new JButton("Việt - Anh");
		p2.setBorder(BorderFactory.createTitledBorder("Tùy chọn"));
		p2.add(btAnhViet, new BorderLayout().SOUTH);
		p2.add(btVietAnh, new BorderLayout().SOUTH);
		p2.setPreferredSize(new Dimension(400, 70));

                p_main.add(p2);
		// p3 Danh sach tu
		JPanel p3 = new JPanel();
		p3.setLayout(new BorderLayout());
		p3.setBorder(BorderFactory.createTitledBorder("Danh sách từ"));
		p3.setPreferredSize(new Dimension(300, 400));
        
                listModel=new DefaultListModel<String>();
		list=new JList<String>(listModel);
		list.setBorder(BorderFactory.createTitledBorder(""));
		p3.add(new JScrollPane(list),BorderLayout.CENTER);
                ReloadJListEV();
                p_main.add(p3);
       
        
        p4 = new JPanel(new BorderLayout());
		tx = new JTextArea(44, 33);
		p4.add(tx);
                p4.setBorder(BorderFactory.createTitledBorder("Nghĩa của từ"));
		p4.setPreferredSize(new Dimension(400, 400));
        p_main.add(p4);
        
        JPanel p5 = new JPanel();
	tfThemTuAnh = new JTextField(15);
        tfThemTuViet = new JTextField(15);
        JLabel lbThemAnh = new JLabel("Tiếng Anh:");
        JLabel lbThemViet = new JLabel("Tiếng Việt:");
	btThem = new JButton("Thêm");
        p5.setBorder(BorderFactory.createTitledBorder("Thêm từ"));
		p5.add(lbThemAnh, new BorderLayout().SOUTH);
		p5.add(tfThemTuAnh, new BorderLayout().SOUTH);
                p5.add(lbThemViet, new BorderLayout().SOUTH);
                p5.add(tfThemTuViet, new BorderLayout().SOUTH);
		p5.add(btThem, new BorderLayout().SOUTH);
		p5.setPreferredSize(new Dimension(300, 140));
                
        p_main.add(p5);
        
        JPanel p6 = new JPanel();
	tfSuaAnh = new JTextField(25);
        tfSuaViet = new JTextField(25);
        JLabel lbSuaAnh = new JLabel("Tiếng Anh");
        JLabel lbSuaViet = new JLabel("Tiếng Việt");
	btSua = new JButton("Sửa");
        p6.setBorder(BorderFactory.createTitledBorder("Sửa từ"));
		p6.add(lbSuaAnh, new BorderLayout().WEST);
                p6.add(tfSuaAnh, new BorderLayout().CENTER);
                p6.add(lbSuaViet, new BorderLayout().WEST);
		p6.add(tfSuaViet, new BorderLayout().CENTER);
		p6.add(btSua, new BorderLayout().SOUTH);
		p6.setPreferredSize(new Dimension(400, 140));
                
        p_main.add(p6);
        
        
        btDich.addActionListener(this);
        btAnhViet.addActionListener(this);
        btVietAnh.addActionListener(this);
        btThem.addActionListener(this);
        btSua.addActionListener(this);
        
        list.addListSelectionListener(new ListSelectionListener() {
			
			@Override
			public void valueChanged(ListSelectionEvent e) {
				
				 JList source = (JList) e.getSource();
                                 String word = (String) source.getSelectedValue();
                                 //tim tu trong file roi dich
                                 if(flag == 0){
                                     int find = translate.findWordEnglish(word);
                                    if(find!= -1){
                                        tx.setText(translate.getVietNam(find));
                                    }
                                 }
                                 
                                 if(flag == 1){
                                     int find = translate.findWordVietNam(word);
                                        if(find!= -1){
                                            tx.setText(translate.getEnglish(find));
                                        }
                
                                 }
                                 tfNhap.setText(word);
                              
                                 
                }
        });
        this.add(p_main);
    }
    @Override
	public void actionPerformed(ActionEvent e) {
            Object o = e.getSource();
		
		if(o.equals(btAnhViet)){
			// load danh sach tieng anh
                    flag = 0;
                try {
                    translate.docFileAnh();
                } catch (IOException ex) {
                    Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
                }
                    ReloadJListEV();
		}
		if(o.equals(btVietAnh)){
			//load danh sach tieng viet
                    flag =1;
                try {
                    translate.docFileViet();
                } catch (IOException ex) {
                    Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
                }
                    ReloadJListVE();
		}
                
                if(o.equals(btDich)&& flag== 0){
                    // dich sang tieng anh
                    String word = tfNhap.getText();
                    int find = translate.findWordEnglish(word);
                    if(find > -1){
                        tx.setText(translate.getVietNam(find));
                    }else{
                        tx.setText("Không tìm thấy từ");
                                        }
                }
                
                if(o.equals(btDich) && flag == 1){
                    String word = tfNhap.getText();
                    int find = translate.findWordVietNam(word);
                    if(find!= -1){
                        tx.setText(translate.getEnglish(find));
                    }
                    else{
                        tx.setText("Không tìm thấy từ");
                                        }
                }
                
                if(o.equals(btThem) && flag == 0){
                    String anh = tfThemTuAnh.getText();
                    String viet = tfThemTuViet.getText();
                    if(translate.findWordEnglish(anh)!=-1){
                        JOptionPane.showMessageDialog(this,
                            "Từ này đã có trong danh sách");
                        tfThemTuAnh.setText("");
                        tfThemTuViet.setText("");
                    }
                    else{
                        translate.ghiFileAnh(anh+":"+viet);
                        try {
                            translate.docFileAnh();
                        } catch (IOException ex) {
                            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        ReloadJListEV();
                        tfThemTuAnh.setText("");
                        tfThemTuViet.setText("");
                    }
                }
                if(o.equals(btThem) && flag == 1){
                    String anh = tfThemTuAnh.getText();
                    String viet = tfThemTuViet.getText();
                    if(translate.findWordVietNam(anh)!=-1){
                        JOptionPane.showMessageDialog(this,
                            "Từ này đã có trong danh sách");
                        tfThemTuAnh.setText("");
                        tfThemTuViet.setText("");
                        
                    }
                    else{
                        translate.ghiFileViet(viet+":"+anh);
                        try {
                            translate.docFileViet();
                        } catch (IOException ex) {
                            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        ReloadJListVE();
                        tfThemTuAnh.setText("");
                        tfThemTuViet.setText("");
                    }
                }
                
                if(o.equals(btSua) && flag == 0){
                    String viet = tfSuaViet.getText();
                    String anh = tfSuaAnh.getText();
                    int viTriTuGoc = translate.findWordEnglish(anh);
                    if(viTriTuGoc == -1){
                        JOptionPane.showMessageDialog(this,
                            "Từ này không có trong danh sách");
                        tfThemTuAnh.setText("");
                        tfThemTuViet.setText("");
                    }
                    else{
                        translate.xoaTu(viTriTuGoc);
                        translate.ghiFileAnh(anh+":"+viet+"\n");
                        translate.setSoLuong(translate.getSoTu()+1);
                        try {
                            translate.docFileAnh();
                        } catch (IOException ex) {
                            Logger.getLogger(Dictionary.class.getName()).log(Level.SEVERE, null, ex);
                        }
                        ReloadJListEV();
                        tfThemTuAnh.setText("");
                        tfThemTuViet.setText("");
                    }
                }
               
        }
        
        public void ReloadJListEV(){
            listModel.clear();
            ArrayList<String> tmp = new ArrayList<>(); 
            for(int i = 0; i < translate.getSoTu()+1; i++){
                tmp.add(translate.getEnglish(i));
            }
            Collections.sort(tmp);
            tmp.stream().forEach((t) -> {
                listModel.addElement(t);
        });
        }
        
        public void ReloadJListVE(){
            listModel.clear();
            ArrayList<String> tmp = new ArrayList<>(); 
            for(int i = 0; i < translate.getSoTu()+1; i++){
                tmp.add(translate.getVietNam(i));
            }
            Collections.sort(tmp);
            tmp.stream().forEach((t) -> {
                listModel.addElement(t);
        });
        }
        

}

