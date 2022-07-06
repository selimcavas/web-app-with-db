package com.paymentdb;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;    

public class gui 
{
    static String SELECTED_PRODUCT = null;
    static int SELECTED_PRICE = 0;
    public static void main( String[] args )
    {
        //setting frames
       final JFrame buyFrame = new JFrame("Buy A Product");
        buyFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            buyFrame.setSize(500,300);

       final JFrame confirmFrame = new JFrame("Confirm Purchase");
         confirmFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            confirmFrame.setSize(300,150);
        
        final JFrame purchaseSuccess = new JFrame("");
         purchaseSuccess.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            purchaseSuccess.setSize(300,150);

       JPanel mainBuyPanel = new JPanel(new BorderLayout());
       JPanel productPanel = new JPanel();
       JPanel confirmPanel = new JPanel(new BorderLayout());
       JPanel confirmButtonsPanel = new JPanel();
       JPanel purchaseSuccessPanel = new JPanel();

       //setting labels for buy panel and confirm panel
       
       JLabel buyLabel = new JLabel("Select A Product to Buy");
        buyLabel.setPreferredSize(new Dimension(500,50));
        buyLabel.setHorizontalAlignment(JLabel.CENTER);
        buyLabel.setFont(new Font( "Arial", Font.BOLD, 20 ));

       JLabel confirmLabel = new JLabel("Confirm Your Purchase");
        confirmLabel.setPreferredSize(new Dimension(500,50));
        confirmLabel.setHorizontalAlignment(JLabel.CENTER);
        confirmLabel.setFont(new Font( "Arial", Font.BOLD, 20 ));
        
        
        JLabel tickLabel = new JLabel("Purchase Successful");
        tickLabel.setPreferredSize(new Dimension(500,50));
        tickLabel.setHorizontalAlignment(JLabel.CENTER);
        tickLabel.setFont(new Font( "Arial", Font.BOLD, 20 ));
        tickLabel.setForeground(new java.awt.Color(0, 153, 0));

        //setting buttons for buy panel and confirm panel

        JButton product1 = new JButton("Product 1");
        product1.setPreferredSize(new Dimension(100, 100));

        JButton product2 = new JButton("Product 2");
        product2.setPreferredSize(new Dimension(100, 100));

        JButton product3 = new JButton("Product 3");
        product3.setPreferredSize(new Dimension(100, 100));

        JButton confirm = new JButton("OK");
        confirm.setPreferredSize(new Dimension(100, 50));

        JButton cancel = new JButton("Cancel");
        cancel.setPreferredSize(new Dimension(100, 50));

        JButton buyMore = new JButton("Buy More");
        buyMore.setPreferredSize(new Dimension(100, 50));

        
        confirmPanel.add(confirmLabel, BorderLayout.NORTH);
        confirmButtonsPanel.add(cancel);
        confirmButtonsPanel.add(confirm);
        confirmPanel.add(confirmButtonsPanel, BorderLayout.SOUTH);

        confirmFrame.add(confirmPanel);

        purchaseSuccessPanel.add(tickLabel);
        purchaseSuccessPanel.add(buyMore);

        purchaseSuccess.add(purchaseSuccessPanel);

        productPanel.add(product1);
        productPanel.add(product2);
        productPanel.add(product3);

        mainBuyPanel.add(buyLabel, BorderLayout.NORTH);
        mainBuyPanel.add(productPanel, BorderLayout.CENTER);

        buyFrame.add(mainBuyPanel);

        product1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                SELECTED_PRODUCT = "Product 1";
                SELECTED_PRICE = 100;
                buyFrame.setVisible(false);
                confirmFrame.setLocationRelativeTo(null);
                confirmFrame.setVisible(true);

            }
        });

        product2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                SELECTED_PRODUCT = "Product 2";
                SELECTED_PRICE = 200;
                buyFrame.setVisible(false);
                confirmFrame.setLocationRelativeTo(null);
                confirmFrame.setVisible(true);
            }
        });

        product3.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                SELECTED_PRODUCT = "Product 3";
                SELECTED_PRICE = 300;
                buyFrame.setVisible(false);
                confirmFrame.setLocationRelativeTo(null);
                confirmFrame.setVisible(true);
            }
        });

        //confirm button adding to db will be done here
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                Database.insertProduct(SELECTED_PRODUCT, SELECTED_PRICE);
                confirmFrame.setVisible(false);
                purchaseSuccess.setLocationRelativeTo(null);
                purchaseSuccess.setVisible(true);
            }
        });

        cancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                confirmFrame.setVisible(false);
                buyFrame.setLocationRelativeTo(null);
                buyFrame.setVisible(true);
            }
        });

        buyMore.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                purchaseSuccess.setVisible(false);
                buyFrame.setLocationRelativeTo(null);
                buyFrame.setVisible(true);
            }
        });

       buyFrame.setLocationRelativeTo(null);
       buyFrame.setVisible(true);
       
    }
}
