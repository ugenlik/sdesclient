/*
 * SDES_ClientView.java
 */

package sdes_client;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.jdesktop.application.SingleFrameApplication;
import org.jdesktop.application.FrameView;


/**
 * The application's main frame.
 */

public class SDES_ClientView extends FrameView {
    
    Socket sock;
    
    String [][] SBOX1 = {{"01","00","11","10"},{"11","10","01","00"},{"00","10","01","11"},{"11","01","11","10"}};
    String [][] SBOX2 = {{"00","01","10","11"},{"10","00","01","11"},{"11","00","01","00"},{"10","01","00","11"}};
    
    public String P10(String in)
    {
        ArrayList<Character> output = new ArrayList<Character>();
        
        output.add(in.charAt(2));
        output.add(in.charAt(4));
        output.add(in.charAt(1));
        output.add(in.charAt(6));
        output.add(in.charAt(3));
        output.add(in.charAt(9));
        output.add(in.charAt(0));
        output.add(in.charAt(8));
        output.add(in.charAt(7));
        output.add(in.charAt(5));
        
        String returnStr = "";
        for(int i=0; i<output.size(); i++)
            returnStr+=output.get(i).toString();
        return returnStr;
    }
    
    public String LS1(String in)
    {
        ArrayList<Character> output = new ArrayList<Character>();
        
        output.add(in.charAt(1));
        output.add(in.charAt(2));
        output.add(in.charAt(3));
        output.add(in.charAt(4));
        output.add(in.charAt(0));
        output.add(in.charAt(6));
        output.add(in.charAt(7));
        output.add(in.charAt(8));
        output.add(in.charAt(9));
        output.add(in.charAt(5));
        
        String returnStr = "";
        for(int i=0; i<output.size(); i++)
            returnStr+=output.get(i).toString();
        return returnStr;
    }
    
    public String LS2(String in)
    {
        ArrayList<Character> output = new ArrayList<Character>();
        
        output.add(in.charAt(2));
        output.add(in.charAt(3));
        output.add(in.charAt(4));
        output.add(in.charAt(0));
        output.add(in.charAt(1));
        output.add(in.charAt(7));
        output.add(in.charAt(8));
        output.add(in.charAt(9));
        output.add(in.charAt(5));
        output.add(in.charAt(6));
        
        String returnStr = "";
        for(int i=0; i<output.size(); i++)
            returnStr+=output.get(i).toString();
        return returnStr;
    }
    
    public String P8(String in)
    {
        ArrayList<Character> output = new ArrayList<Character>();
        
        output.add(in.charAt(5));
        output.add(in.charAt(2));
        output.add(in.charAt(6));
        output.add(in.charAt(3));
        output.add(in.charAt(7));
        output.add(in.charAt(4));
        output.add(in.charAt(9));
        output.add(in.charAt(8));
        
        String returnStr = "";
        for(int i=0; i<output.size(); i++)
            returnStr+=output.get(i).toString();
        return returnStr;
    }
    
    public String P4(String in)
    {
        ArrayList<Character> output = new ArrayList<Character>();
        
        output.add(in.charAt(1));
        output.add(in.charAt(3));
        output.add(in.charAt(2));
        output.add(in.charAt(0));
        
        String returnStr = "";
        for(int i=0; i<output.size(); i++)
            returnStr+=output.get(i).toString();
        return returnStr;
    }
    
    public String EP(String in)
    {
        ArrayList<Character> output = new ArrayList<Character>();
        
        output.add(in.charAt(3));
        output.add(in.charAt(0));
        output.add(in.charAt(1));
        output.add(in.charAt(2));
        output.add(in.charAt(1));
        output.add(in.charAt(2));
        output.add(in.charAt(3));
        output.add(in.charAt(0));
        
        String returnStr = "";
        for(int i=0; i<output.size(); i++)
            returnStr+=output.get(i).toString();
        return returnStr;
    }
    
    public String IP(String in)
    {
        ArrayList<Character> output = new ArrayList<Character>();
        
        output.add(in.charAt(1));
        output.add(in.charAt(5));
        output.add(in.charAt(2));
        output.add(in.charAt(0));
        output.add(in.charAt(3));
        output.add(in.charAt(7));
        output.add(in.charAt(4));
        output.add(in.charAt(6));
        
        String returnStr = "";
        for(int i=0; i<output.size(); i++)
            returnStr+=output.get(i).toString();
        return returnStr;
    }
    
    public String IP_INV(String in)
    {
        ArrayList<Character> output = new ArrayList<Character>();
        
        output.add(in.charAt(3));
        output.add(in.charAt(0));
        output.add(in.charAt(2));
        output.add(in.charAt(4));
        output.add(in.charAt(6));
        output.add(in.charAt(1));
        output.add(in.charAt(7));
        output.add(in.charAt(5));
        
        String returnStr = "";
        for(int i=0; i<output.size(); i++)
            returnStr+=output.get(i).toString();
        return returnStr;
    }
    
    public String SW(String in)
    {
        ArrayList<Character> output = new ArrayList<Character>();
        
        output.add(in.charAt(4));
        output.add(in.charAt(5));
        output.add(in.charAt(6));
        output.add(in.charAt(7));
        output.add(in.charAt(0));
        output.add(in.charAt(1));
        output.add(in.charAt(2));
        output.add(in.charAt(3));
        
        String returnStr = "";
        for(int i=0; i<output.size(); i++)
            returnStr+=output.get(i).toString();
        return returnStr;
    }
    
    public String generateKey1(String in)
    {
        String returnStr = P10(in);
        returnStr = LS1(returnStr);
        return P8(returnStr);
    }
    
    public String generateKey2(String in)
    {
        String returnStr = P10(in);
        returnStr = LS1(returnStr);
        returnStr = LS2(returnStr);
        return P8(returnStr);
    }
    
    public String XOR(String in, String in2, int len)
    {
        ArrayList<Character> output = new ArrayList<Character>();

        for(int i=0; i<len; i++)
        {
            char temp = '0';
            if(in.charAt(i) == '1' && in2.charAt(i) == '1');
            else if(in.charAt(i) == '1' || in2.charAt(i) == '1')
                temp = '1';
            output.add(temp);
        }
        
        String returnStr = "";
        for(int i=0; i<output.size(); i++)
            returnStr+=output.get(i).toString();
        return returnStr;
    }
    
    public String F(String in, String key)
    {
        String right = in.substring(4);
        String left = in.substring(0, 4);
        String right2 = XOR(EP(right), key, 8).substring(4);
        String left2 = XOR(EP(right), key, 8).substring(0,4);
        
        String right3 = SBOX2[Integer.parseInt(right2.substring(0, 1)+right2.substring(3, 4), 2)][Integer.parseInt(right2.substring(1, 2)+right2.substring(2, 3), 2)];
        String left3 = SBOX1[Integer.parseInt(left2.substring(0, 1)+left2.substring(3, 4), 2)][Integer.parseInt(left2.substring(1, 2)+left2.substring(2, 3), 2)];
        
        return XOR(left, P4(left3+right3), 4)+right;
    }
    
    public String Encrypt(String in)
    {
        
        ArrayList<Character> output = new ArrayList<Character>();
        
        String Key1 = generateKey1(key_field.getText());
        String Key2 = generateKey2(key_field.getText());
        
        for(int i=0; i<in.length(); i++)
        {
            String temp = Integer.toBinaryString((int)in.charAt(i));
            for(int j=0; j<8-temp.length(); j++)
                temp = "0"+temp;
            temp = IP(temp);
            temp = F(temp, Key1);
            temp = SW(temp);
            temp = F(temp, Key2);
            temp = IP_INV(temp);
            output.add((char)Integer.parseInt(temp, 2));
        }
        
        String returnStr = "";
        for(int i=0; i<output.size(); i++)
            returnStr+=output.get(i).toString();
        return returnStr;
    }
    
    public String Decrypt(String in)
    {
        
        ArrayList<Character> output = new ArrayList<Character>();
        
        String Key1 = generateKey1(key_field.getText());
        String Key2 = generateKey2(key_field.getText());
        
        for(int i=0; i<in.length(); i++)
        {
            String temp = Integer.toBinaryString((int)in.charAt(i));
            for(int j=0; j<8-temp.length(); j++)
                temp = "0"+temp;
            temp = IP(temp);
            temp = F(temp, Key2);
            temp = SW(temp);
            temp = F(temp, Key1);
            temp = IP_INV(temp);
            output.add((char)Integer.parseInt(temp, 2));
        }
        
        String returnStr = "";
        for(int i=0; i<output.size(); i++)
            returnStr+=output.get(i).toString();
        return returnStr;
    }
    
    class ConnectThread implements Runnable {

        public Socket sock;

        ConnectThread(Socket _sock) {
            sock = _sock;
        }

        public void run()
        {
            char[] recieved = new char[512];
            try {
                BufferedReader br = new BufferedReader(new InputStreamReader(sock.getInputStream()));
                while (true) {
                    br.read(recieved);
                    output_text.setText(output_text.getText()+"Peer says: "+Decrypt(new String(recieved))+"\n");
                }

            } catch (Exception ex) {
                System.out.println("Exception at ConnectThread");
            }
        }
    }

    public SDES_ClientView(SingleFrameApplication app) {
        super(app);

        initComponents();
    }
    
    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        mainPanel = new javax.swing.JPanel();
        host_field = new javax.swing.JTextField();
        port_field = new javax.swing.JTextField();
        key_field = new javax.swing.JTextField();
        connect_button = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        output_text = new javax.swing.JTextArea();
        input_field = new javax.swing.JTextField();
        send_button = new javax.swing.JButton();

        mainPanel.setName("mainPanel"); // NOI18N

        org.jdesktop.application.ResourceMap resourceMap = org.jdesktop.application.Application.getInstance(sdes_client.SDES_ClientApp.class).getContext().getResourceMap(SDES_ClientView.class);
        host_field.setText(resourceMap.getString("host_field.text")); // NOI18N
        host_field.setName("host_field"); // NOI18N

        port_field.setText(resourceMap.getString("port_field.text")); // NOI18N
        port_field.setName("port_field"); // NOI18N

        key_field.setText(resourceMap.getString("key_field.text")); // NOI18N
        key_field.setName("key_field"); // NOI18N

        connect_button.setText(resourceMap.getString("connect_button.text")); // NOI18N
        connect_button.setName("connect_button"); // NOI18N
        connect_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                connect_buttonActionPerformed(evt);
            }
        });

        jScrollPane1.setName("jScrollPane1"); // NOI18N

        output_text.setColumns(20);
        output_text.setRows(5);
        output_text.setName("output_text"); // NOI18N
        jScrollPane1.setViewportView(output_text);

        input_field.setName("input_field"); // NOI18N

        send_button.setText(resourceMap.getString("send_button.text")); // NOI18N
        send_button.setName("send_button"); // NOI18N
        send_button.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                send_buttonActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout mainPanelLayout = new org.jdesktop.layout.GroupLayout(mainPanel);
        mainPanel.setLayout(mainPanelLayout);
        mainPanelLayout.setHorizontalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                    .add(mainPanelLayout.createSequentialGroup()
                        .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.TRAILING)
                            .add(port_field, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 196, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                            .add(host_field, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 196, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(connect_button, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 155, Short.MAX_VALUE))
                    .add(key_field, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 196, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(input_field, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE)
                    .add(send_button, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 360, Short.MAX_VALUE))
                .addContainerGap())
        );
        mainPanelLayout.setVerticalGroup(
            mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(mainPanelLayout.createSequentialGroup()
                .addContainerGap()
                .add(mainPanelLayout.createParallelGroup(org.jdesktop.layout.GroupLayout.BASELINE)
                    .add(host_field, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                    .add(connect_button, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 27, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(port_field, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(key_field, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 166, Short.MAX_VALUE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(input_field, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.UNRELATED)
                .add(send_button)
                .add(42, 42, 42))
        );

        setComponent(mainPanel);
    }// </editor-fold>//GEN-END:initComponents

private void connect_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_connect_buttonActionPerformed
    try {
        sock = new Socket(host_field.getText(), Integer.parseInt(port_field.getText()));
    } catch (UnknownHostException ex) {
        Logger.getLogger(SDES_ClientView.class.getName()).log(Level.SEVERE, null, ex);
    } catch (IOException ex) {
        Logger.getLogger(SDES_ClientView.class.getName()).log(Level.SEVERE, null, ex);
    }
    Thread connectT = new Thread(new ConnectThread(sock));
    connectT.start();
}//GEN-LAST:event_connect_buttonActionPerformed

private void send_buttonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_send_buttonActionPerformed
        try {
            PrintWriter pw = new PrintWriter(sock.getOutputStream(), true);
            pw.print(Encrypt(input_field.getText()));
            pw.flush();
            output_text.setText(output_text.getText()+"Me: "+input_field.getText()+"\n");
        } catch (Exception ex) {
            System.out.println("Exception at Send Button Action Performed");
        }
}//GEN-LAST:event_send_buttonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton connect_button;
    private javax.swing.JTextField host_field;
    private javax.swing.JTextField input_field;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField key_field;
    private javax.swing.JPanel mainPanel;
    private javax.swing.JTextArea output_text;
    private javax.swing.JTextField port_field;
    private javax.swing.JButton send_button;
    // End of variables declaration//GEN-END:variables
}
