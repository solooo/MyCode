package solooo.mycode.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Title:
 * Description:
 * Copyright:Copyright 2016 HtDataCloud
 * Author:裴健(peij@htdatacloud.com)
 * Date:2016/5/3
 * History:
 * his1:
 */
public class CardTest extends JFrame {
//    public static final Image LOG = new ImageIcon(
//            CardTest.class.getResource("/images/LOG.png")).getImage();
    private CardLayout cardLayout;

    private JPanel imagePanel;
    private ImageLabel[] imageLabels;

    private JPanel buttonPanel;
    private JButton firstButton;
    private JButton previousButton;
    private JButton nextButton;
    private JButton lasButton;

    public CardTest() {
        super("CardLayout Test --by  Alog2012");
        init();
    }

    private void init() {
        Container container = getContentPane();
        container.add(getImagePanel(), BorderLayout.CENTER);
        container.add(getButtonPanel(), BorderLayout.SOUTH);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        com.sun.awt.AWTUtilities.setWindowOpacity(this, 0.95f);
        pack();
        // 获取系统工具箱
        Toolkit kit = Toolkit.getDefaultToolkit();
        int scrW = kit.getScreenSize().width;
        int scrH = kit.getScreenSize().height;
        int locale_x = (scrW - getSize().width) / 2;
        int locale_y = (scrH - getSize().height) / 2;
        super.setLocation(locale_x, locale_y);
//        super.setIconImage(LOG);
        setVisible(true);
    }

    protected JPanel getImagePanel() {
        imagePanel = new JPanel(cardLayout = new CardLayout());
        setMinimumSize(new Dimension(240, 200));
        setPreferredSize(new Dimension(540, 450));
        setMaximumSize(new Dimension(720, 640));
        imageLabels = getImageLabels();
        for (int i = 0; i < 4; i++) {
            imagePanel.add(imageLabels[i], String.valueOf(i));
        }
        return imagePanel;
    }

    protected ImageLabel[] getImageLabels() {
        imageLabels = new ImageLabel[4];

        for (int i = 0; i < 4; i++) {
            Image image = new ImageIcon("images/pic_" + i + ".jpg")
                    .getImage();
            imageLabels[i] = new ImageLabel(image);
        }
        return imageLabels;
    }

    protected JPanel getButtonPanel() {
        buttonPanel = new JPanel();
        buttonPanel.add(getFirstButton());
        buttonPanel.add(getPreviousButton());
        buttonPanel.add(getNextButton());
        buttonPanel.add(getLasButton());
        return buttonPanel;
    }

    protected JButton getFirstButton() {
        firstButton = new JButton("首页");
        firstButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.first(imagePanel);
            }
        });
        return firstButton;
    }

    protected JButton getPreviousButton() {
        previousButton = new JButton("向前");
        previousButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.previous(imagePanel);
            }
        });
        return previousButton;
    }

    protected JButton getNextButton() {
        nextButton = new JButton("向后");
        nextButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.next(imagePanel);
            }
        });
        return nextButton;
    }

    protected JButton getLasButton() {
        lasButton = new JButton("末页");
        lasButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cardLayout.last(imagePanel);
            }
        });
        return lasButton;
    }

    class ImageLabel extends JLabel {
        private Image image;

        public ImageLabel(Image image) {
            this.image = image;
        }

        protected void paintComponent(Graphics g) {
            g.drawImage(image, 0, 0, getSize().width, getSize().height, this);
        }
    }

    public static void main(String[] args) {
        try {
            UIManager
                    .setLookAndFeel("com.sun.java.swing.plaf.nimbus.NimbusLookAndFeel");
        } catch (Exception e) {
            e.printStackTrace();
        }
        new CardTest();
    }
}
