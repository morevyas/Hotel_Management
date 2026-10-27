package hotel.management.system;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

/** Redesigned hotel operations dashboard. */
public class Dashboard extends JFrame implements ActionListener {
    private static final Color NAVY = new Color(18,  35, 55);
    private static final Color GOLD = new Color(214, 166, 83);
    private static final Color BG = new Color(246, 248, 251);
    private final JPanel content = new JPanel(new BorderLayout(18,18));
    private JLabel roomsValue, guestsValue, bookingsValue, revenueValue;
    private JButton reception, addRoom, employees, reservations, billing, housekeeping, services, reports, logout;

    public Dashboard() {
        setTitle("Grand View | Hotel Operations"); setSize(1250, 800); setMinimumSize(new Dimension(1050,680));
        setLocationRelativeTo(null); setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); setLayout(new BorderLayout());
        getContentPane().setBackground(BG);
        JPanel sidebar = new JPanel(); sidebar.setBackground(NAVY); sidebar.setPreferredSize(new Dimension(225, 0));
        sidebar.setLayout(new BoxLayout(sidebar, BoxLayout.Y_AXIS)); sidebar.setBorder(new EmptyBorder(26,18,20,18));
        JLabel brand = new JLabel("GRAND VIEW"); brand.setForeground(Color.WHITE); brand.setFont(new Font("SansSerif",Font.BOLD,23));
        JLabel sub = new JLabel("HOTEL MANAGEMENT"); sub.setForeground(new Color(190,199,211)); sub.setFont(new Font("SansSerif",Font.PLAIN,10));
        sidebar.add(brand); sidebar.add(Box.createVerticalStrut(5)); sidebar.add(sub); sidebar.add(Box.createVerticalStrut(34));
        reception = nav("Reception & Guests"); addRoom = nav("Rooms & Inventory"); employees = nav("Staff Directory");
        reservations = nav("Reservations"); billing = nav("Billing & Invoices"); housekeeping = nav("Housekeeping");
        services = nav("Guest Services"); reports = nav("Reports & Analytics"); logout = nav("Logout");
        for (JButton b : new JButton[]{reception, reservations, addRoom, billing, housekeeping, services, employees, reports}) { sidebar.add(b); sidebar.add(Box.createVerticalStrut(7)); }
        sidebar.add(Box.createVerticalGlue()); sidebar.add(logout); add(sidebar,BorderLayout.WEST);
        content.setBackground(BG); content.setBorder(new EmptyBorder(28,30,28,30)); add(content,BorderLayout.CENTER);
        showHome(); setVisible(true);
    }
    private JButton nav(String label) { JButton b=new JButton(label); b.setAlignmentX(Component.LEFT_ALIGNMENT); b.setMaximumSize(new Dimension(190,42)); b.setPreferredSize(new Dimension(190,42)); b.setFocusPainted(false); b.setForeground(new Color(235,239,245)); b.setBackground(new Color(31,49,72)); b.setBorder(new EmptyBorder(10,12,10,8)); b.setHorizontalAlignment(SwingConstants.LEFT); b.addActionListener(this); return b; }
    private void showHome() {
        content.removeAll(); JPanel top=new JPanel(new BorderLayout()); top.setOpaque(false);
        JLabel title=new JLabel("Good day, Hotel Manager"); title.setFont(new Font("SansSerif",Font.BOLD,28)); title.setForeground(NAVY);
        JLabel date=new JLabel("HOTEL OPERATIONS  /  OVERVIEW"); date.setFont(new Font("SansSerif",Font.BOLD,11)); date.setForeground(new Color(112,124,140));
        JPanel titles=new JPanel(new GridLayout(2,1,0,4)); titles.setOpaque(false); titles.add(title); titles.add(date); top.add(titles,BorderLayout.WEST);
        JButton refresh=new JButton("Refresh overview"); refresh.addActionListener(e->refreshStats()); top.add(refresh,BorderLayout.EAST); content.add(top,BorderLayout.NORTH);
        JPanel center=new JPanel(new BorderLayout(0,22)); center.setOpaque(false);
        JPanel stats=new JPanel(new GridLayout(1,4,15,0)); stats.setOpaque(false);
        roomsValue=new JLabel("—"); guestsValue=new JLabel("—"); bookingsValue=new JLabel("—"); revenueValue=new JLabel("—");
        stats.add(statCard("ROOMS IN INVENTORY",roomsValue,"Room availability at a glance")); stats.add(statCard("CURRENT GUESTS",guestsValue,"Checked-in guest records"));
        stats.add(statCard("ACTIVE BOOKINGS",bookingsValue,"Upcoming / in-house stays")); stats.add(statCard("PAYMENTS RECORDED",revenueValue,"Total deposits recorded"));
        center.add(stats,BorderLayout.NORTH);
        JPanel lower=new JPanel(new GridLayout(1,2,18,0)); lower.setOpaque(false);
        JPanel quick=panel("QUICK ACTIONS"); quick.setLayout(new GridLayout(4,2,10,10));
        quick.add(actionButton("＋  New guest",()->openOld("guest"))); quick.add(actionButton("▦  Room inventory",()->openOld("rooms")));
        quick.add(actionButton("▤  Reservations",()->openModule("reservations"))); quick.add(actionButton("₹  Create invoice",()->openModule("billing")));
        quick.add(actionButton("✓  Housekeeping",()->openModule("housekeeping"))); quick.add(actionButton("✧  Guest services",()->openModule("services")));
        quick.add(actionButton("♙  Staff directory",()->openOld("staff"))); quick.add(actionButton("▥  Reports",()->openModule("reports")));
        JPanel info=panel("TODAY'S WORKFLOW"); info.setLayout(new BoxLayout(info,BoxLayout.Y_AXIS));
        info.add(note("01", "Front desk", "Register guests, search rooms and manage checkout."));
        info.add(Box.createVerticalStrut(16)); info.add(note("02", "Room operations", "Keep room availability and cleaning status up to date."));
        info.add(Box.createVerticalStrut(16)); info.add(note("03", "Revenue & service", "Track invoices, service requests and operational reports."));
        lower.add(quick); lower.add(info); center.add(lower,BorderLayout.CENTER); content.add(center,BorderLayout.CENTER);
        refreshStats(); content.revalidate(); content.repaint();
    }
    private JPanel statCard(String label,JLabel value,String hint) { JPanel p=new JPanel(new BorderLayout(0,9)); p.setBackground(Color.WHITE); p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(229,233,239)),new EmptyBorder(18,18,18,18))); JLabel l=new JLabel(label); l.setFont(new Font("SansSerif",Font.BOLD,10)); l.setForeground(new Color(105,117,132)); value.setFont(new Font("SansSerif",Font.BOLD,30)); value.setForeground(NAVY); JLabel h=new JLabel(hint); h.setFont(new Font("SansSerif",Font.PLAIN,11)); h.setForeground(new Color(130,140,152)); JPanel middle=new JPanel(new BorderLayout()); middle.setOpaque(false); middle.add(value,BorderLayout.CENTER); p.add(l,BorderLayout.NORTH); p.add(middle,BorderLayout.CENTER); p.add(h,BorderLayout.SOUTH); return p; }
    private JPanel panel(String title) { JPanel p=new JPanel(); p.setBackground(Color.WHITE); p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(229,233,239)),new EmptyBorder(18,18,18,18))); p.setLayout(new BorderLayout(0,15)); JLabel l=new JLabel(title); l.setFont(new Font("SansSerif",Font.BOLD,12)); l.setForeground(NAVY); p.add(l,BorderLayout.NORTH); return p; }
    private JButton actionButton(String text,Runnable action) { JButton b=new JButton(text); b.setHorizontalAlignment(SwingConstants.LEFT); b.setBackground(new Color(249,250,252)); b.setForeground(NAVY); b.setFocusPainted(false); b.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(225,230,237)),new EmptyBorder(12,12,12,12))); b.addActionListener(e->action.run()); return b; }
    private JPanel note(String n,String title,String desc) { JPanel p=new JPanel(new BorderLayout(12,0)); p.setOpaque(false); JLabel num=new JLabel(n); num.setForeground(GOLD); num.setFont(new Font("SansSerif",Font.BOLD,19)); JPanel t=new JPanel(new GridLayout(2,1,0,5)); t.setOpaque(false); JLabel a=new JLabel(title); a.setFont(new Font("SansSerif",Font.BOLD,14)); JLabel d=new JLabel("<html><div style='width:270px'>"+desc+"</div></html>"); d.setForeground(new Color(112,124,140)); d.setFont(new Font("SansSerif",Font.PLAIN,11)); t.add(a);t.add(d);p.add(num,BorderLayout.WEST);p.add(t,BorderLayout.CENTER);return p; }
    private void refreshStats() { try { conn c=new conn(); if(c.c==null) return; roomsValue.setText(count(c.c,"SELECT COUNT(*) FROM room")); guestsValue.setText(count(c.c,"SELECT COUNT(*) FROM customer")); bookingsValue.setText(count(c.c,"SELECT COUNT(*) FROM reservations WHERE status IN ('BOOKED','CHECKED_IN')")); revenueValue.setText("₹ "+sum(c.c,"SELECT COALESCE(SUM(amount),0) FROM invoices")); c.c.close(); } catch(Exception ex) { roomsValue.setText("—"); guestsValue.setText("—"); bookingsValue.setText("—"); revenueValue.setText("—"); } }
    private String count(Connection c,String sql) throws SQLException { try(Statement s=c.createStatement();ResultSet r=s.executeQuery(sql)){return r.next()?r.getString(1):"0";} }
    private String sum(Connection c,String sql) throws SQLException { try(Statement s=c.createStatement();ResultSet r=s.executeQuery(sql)){return r.next()?String.format("%,.0f",r.getDouble(1)):"0";} }
    private void openOld(String key) { try { switch(key) { case "guest":new AddCustomer();break; case "rooms":new Room();break; case "staff":new EmployeeInfo();break; } } catch(Exception ex){JOptionPane.showMessageDialog(this,"Could not open screen: "+ex.getMessage());} }
    private void openModule(String key) { new FeatureModule(key); }
    public void actionPerformed(ActionEvent e) { Object s=e.getSource(); if(s==reception) new Reception(); else if(s==addRoom) new Room(); else if(s==employees) new EmployeeInfo(); else if(s==reservations) openModule("reservations"); else if(s==billing) openModule("billing"); else if(s==housekeeping) openModule("housekeeping"); else if(s==services) openModule("services"); else if(s==reports) openModule("reports"); else if(s==logout){dispose();new HotelManagementSystem();} }
    public static void main(String[] args) { SwingUtilities.invokeLater(Dashboard::new); }
}
