package restaurante;

import Conexion.conectar;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.view.JasperViewer;

public class Ventas extends javax.swing.JInternalFrame {

    public Ventas() {
        initComponents();
        mostrarClientes();
        mostrarMesero();
        mostrarAlimento();
        MostrarDatos("");
    }

    void MostrarDatos(String valor) {
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("#");
        modelo.addColumn("CLIENTE");
        modelo.addColumn("MESERO");
        modelo.addColumn("FECHA");
        modelo.addColumn("PRECIO A PAGAR");

        String sql = "";
        if (valor.equals("")) {
            sql = "SELECT IDREGISTRO,IDCLIENTE,IDMESERO,FECHA_VENTA,PRECIO_APAGAR FROM registro_venta";
        }
        String[] datos = new String[5];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = GetClienteNombre(rs.getInt(2));
                datos[2] = GetMeseroNombre(rs.getInt(3));
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);

                modelo.addRow(datos);
            }
            tablaVentas.setModel(modelo);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
    }

    String GetMeseroNombre(int idmesero) {
        String mesero = "";
        String sql = "SELECT NOMBRES_ME, APELLIDO_ME FROM meseros WHERE IDMESERO = " + idmesero;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                mesero = rs.getString("NOMBRES_ME") + " " + rs.getString("APELLIDO_ME");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return mesero;
    }

    String GetClienteNombre(int idcliente) {
        String cliente = "";
        String sql = "SELECT NOMBRES, APELLIDOS FROM clientes WHERE IDCLIENTE = " + idcliente;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                cliente = rs.getString("NOMBRES") + " " + rs.getString("APELLIDOS");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return cliente;
    }
    
    String GetAlimentoNombre(int idalimento) {
        String alimento = "";
        String sql = "SELECT NOMBRE_ALI FROM alimento WHERE IDALIMENTO = " + idalimento;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                alimento = rs.getString("NOMBRE_ALI");
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return alimento;
    }

    void agregarProducto() {
        double precioAlimento, importe;
        int idalimento, cantidad;
        String alimento, partesAlimento[];

        alimento = (String) alimentosSelec.getSelectedItem();
        partesAlimento = alimento.split("-");

        idalimento = Integer.parseInt(partesAlimento[0]);
        precioAlimento = GetPrecio(idalimento);
        cantidad = Integer.parseInt(cantidadtxt.getText());
        importe = cantidad * precioAlimento;

        DefaultTableModel modelo = (DefaultTableModel) tablaDetalle.getModel();

        modelo.addRow(new Object[]{partesAlimento[0], partesAlimento[1], cantidad, precioAlimento, importe});
        tablaDetalle.setBackground(Color.getHSBColor(0, 102, 154));
        calcularTotal();
    }

    void calcularTotal() {
        double total = 0;
        DefaultTableModel modelo = (DefaultTableModel) tablaDetalle.getModel();
        int filas = modelo.getRowCount();
        for (int i = 0; i < filas; i++) {
            total = total + (Double) tablaDetalle.getValueAt(i, 4);
        }
        totaltxt.setText(total + "");
        cantidadtxt.setText("");
    }

    double GetPrecio(int idAlimento) {
        double precio = 0.00;
        String sql = "SELECT PRECIO FROM alimento WHERE IDALIMENTO = " + idAlimento;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                precio = Double.parseDouble(rs.getString("PRECIO"));
            }
        } catch (Exception e) {
            System.err.println(e);
        }
        return precio;
    }

    void mostrarClientes() {
        clientesSelect.removeAllItems();
        String sql = "Select IDCLIENTE,NOMBRES,APELLIDOS from clientes";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                clientesSelect.addItem(rs.getString("IDCLIENTE") + "-" + rs.getString("NOMBRES") + " " + rs.getString("APELLIDOS"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void mostrarMesero() {
        meseroSelect.removeAllItems();
        String sql = "Select IDMESERO,NOMBRES_ME,APELLIDO_ME from meseros";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                meseroSelect.addItem(rs.getString("IDMESERO") + "-" + rs.getString("NOMBRES_ME") + " " + rs.getString("APELLIDO_ME"));
            }
        } catch (SQLException ex) {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void mostrarAlimento() {
        alimentosSelec.removeAllItems();
        String sql = "Select IDALIMENTO,NOMBRE_ALI from alimento";
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                alimentosSelec.addItem(rs.getString("IDALIMENTO") + "-" + rs.getString("NOMBRE_ALI") + " ");
            }
        } catch (SQLException ex) {
            Logger.getLogger(this.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String fechaactual() {
        Date fecha = new Date();
        SimpleDateFormat formatofecha = new SimpleDateFormat("dd/MM/YYYY");
        String resul = formatofecha.format(fecha);
        return resul;
    }

    void GuardarDetalle(String idalimento, int idventa, int cantidad, double precio, double importe) {
        String sql = "INSERT INTO detalle_registro_venta(IDALIMENTO,IDREGISTRO,CANTIDAD,PRECIO_UNITARIO,IMPORTE ) VALUES (?,?,?,?,?)";
        try {
            PreparedStatement psv = cn.prepareStatement(sql);
            psv.setInt(1, Integer.parseInt(idalimento));
            psv.setInt(2, idventa);
            psv.setString(3, cantidad + "");
            psv.setString(4, precio + "");
            psv.setString(5, importe + "");

            int n = psv.executeUpdate();
            if (n > 0) {
            }
        } catch (SQLException ex) {
            System.err.println("registr0: " + ex);
        }
    }

    void eliminarDetalle(int idventa) {
        String sql = "SELECT IDDETALLE FROM detalle_registro_venta WHERE IDREGISTRO=" + idventa;
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            int[] datos = new int[1];
            while (rs.next()) {
                datos[0] = rs.getInt("IDDETALLE");
            }
            for (int i = 0; i < datos.length; i++) {
                PreparedStatement pst = cn.prepareStatement("DELETE FROM detalle_registro_venta WHERE IDDETALLE=" + datos[i]);
                pst.executeUpdate();
            }
        } catch (SQLException ex) {
            System.err.println("registr0: " + ex);
        }
    }
    
    void imprimir(){
        try {
            // TODO add your handling code here:
            List<ProductoReport> listaProductos = new ArrayList<ProductoReport>();
            int filas= tablaDetalle.getRowCount();
            for (int i = 0; i < filas; i++)
            {
                ProductoReport p = new  ProductoReport(tablaDetalle.getValueAt(i, 0).toString(),tablaDetalle.getValueAt(i, 1).toString(),tablaDetalle.getValueAt(i, 2).toString(),tablaDetalle.getValueAt(i, 3).toString(),tablaDetalle.getValueAt(i, 4).toString());
                listaProductos.add(p);
            }
            //JasperReport reporte = (JasperReport) JRLoader.loadObject("ImpFac.jasper");

            JasperReport reporte=JasperCompileManager.compileReport("Venta.jrxml");

            Map<String, String> parametros = new HashMap<String, String>();
            parametros.put("cliente", clientesSelect.getSelectedItem().toString());
            parametros.put("mesero", meseroSelect.getSelectedItem().toString());
            parametros.put("fecha",fechaactual());
            parametros.put("total",totaltxt.getText());

            JasperPrint jasperPrint = JasperFillManager.fillReport(reporte, parametros, new JRBeanCollectionDataSource(listaProductos));
            JasperViewer visor = new JasperViewer(jasperPrint, false);//Crea una ventana de hoja para mostrar datos...
            visor.setVisible(true);//Muestra el Reporte en pantalla...
            visor.setTitle("Venta");

            JRExporter exporter = new JRPdfExporter();
            exporter.setParameter(JRExporterParameter.JASPER_PRINT, jasperPrint);
            exporter.setParameter(JRExporterParameter.OUTPUT_FILE, new java.io.File("reportesproductos.pdf"));
            exporter.exportReport();

        } catch (JRException ex) {
            System.err.println(ex);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPopupMenu1 = new javax.swing.JPopupMenu();
        eliminarMn = new javax.swing.JMenuItem();
        detalleMn = new javax.swing.JMenuItem();
        jPanel1 = new javax.swing.JPanel();
        clientesSelect = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        meseroSelect = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaDetalle = new javax.swing.JTable();
        agregarBtn = new javax.swing.JButton();
        guardarBtn = new javax.swing.JButton();
        alimentosSelec = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cantidadtxt = new javax.swing.JTextField();
        totaltxt = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaVentas = new javax.swing.JTable();

        eliminarMn.setText("Eliminar");
        eliminarMn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarMnActionPerformed(evt);
            }
        });
        jPopupMenu1.add(eliminarMn);

        detalleMn.setText("Detalle de venta");
        detalleMn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detalleMnActionPerformed(evt);
            }
        });
        jPopupMenu1.add(detalleMn);

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setResizable(true);
        setTitle("Ventas");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        clientesSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Registrar Ventas");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Cliente: ");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Mesero:");

        meseroSelect.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        tablaDetalle.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Id Alimento", "Alimento", "Cantidad", "Precio", "Importe"
            }
        ));
        jScrollPane1.setViewportView(tablaDetalle);

        agregarBtn.setText("Agregar alimento");
        agregarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarBtnActionPerformed(evt);
            }
        });

        guardarBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagenes/guardarBtn.png"))); // NOI18N
        guardarBtn.setText("Guardar Venta");
        guardarBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarBtnActionPerformed(evt);
            }
        });

        alimentosSelec.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel4.setText("Alimento:");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel5.setText("Cantidad:");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel6.setText("Total:");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addGap(6, 6, 6))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(meseroSelect, javax.swing.GroupLayout.Alignment.LEADING, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(clientesSelect, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addComponent(alimentosSelec, javax.swing.GroupLayout.PREFERRED_SIZE, 195, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(cantidadtxt, javax.swing.GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(agregarBtn)
                                .addContainerGap())
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(0, 0, Short.MAX_VALUE))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel6)
                                .addGap(18, 18, 18)
                                .addComponent(totaltxt, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(guardarBtn, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 163, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(jLabel2)
                .addGap(3, 3, 3)
                .addComponent(clientesSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(3, 3, 3)
                .addComponent(meseroSelect, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel4))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(5, 5, 5)
                        .addComponent(agregarBtn, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(cantidadtxt)
                            .addComponent(alimentosSelec, javax.swing.GroupLayout.DEFAULT_SIZE, 30, Short.MAX_VALUE))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 107, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(totaltxt, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addComponent(guardarBtn, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(52, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        tablaVentas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tablaVentas.setComponentPopupMenu(jPopupMenu1);
        jScrollPane2.setViewportView(tablaVentas);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane2)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(6, 6, 6))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(6, 6, 6)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarBtnActionPerformed
        agregarProducto();
    }//GEN-LAST:event_agregarBtnActionPerformed

    private void guardarBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarBtnActionPerformed
        if (totaltxt.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Falts agregar productos");
        } else {
            
            imprimir();

            String sql = "INSERT INTO registro_venta(IDCLIENTE,IDMESERO,FECHA_VENTA,PRECIO_APAGAR) VALUES (?,?,?,?)";
            try {
                PreparedStatement psv = cn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS);

                String cliente = (String) clientesSelect.getSelectedItem();
                String[] partesClientes = cliente.split("-");

                String mesero = (String) meseroSelect.getSelectedItem();
                String[] partesMesero = cliente.split("-");

                psv.setInt(1, Integer.parseInt(partesClientes[0]));
                psv.setInt(2, Integer.parseInt(partesMesero[0]));
                psv.setString(3, fechaactual());
                psv.setString(4, totaltxt.getText());

                int n = psv.executeUpdate();
                if (n > 0) {
                    JOptionPane.showMessageDialog(null, "Datos Guardados Correctamente");
                    MostrarDatos("");
                }
                ResultSet rs = psv.getGeneratedKeys();
                while (rs.next()) {
                    int claveGenerada = rs.getInt(1);
                    DefaultTableModel modelo = (DefaultTableModel) tablaDetalle.getModel();
                    int filas = modelo.getRowCount();

                    for (int i = 0; i < filas; i++) {
                        GuardarDetalle((String) tablaDetalle.getValueAt(i, 0), claveGenerada, (int) tablaDetalle.getValueAt(i, 2), (double) tablaDetalle.getValueAt(i, 3), (double) tablaDetalle.getValueAt(i, 4));
                    }

                }
            } catch (SQLException ex) {
                System.err.println("registr0: " + ex);
            }
        }
    }//GEN-LAST:event_guardarBtnActionPerformed

    private void eliminarMnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarMnActionPerformed
        int fila = tablaVentas.getSelectedRow();
        int idventa = Integer.parseInt(tablaVentas.getValueAt(fila, 0).toString());
        eliminarDetalle(idventa);
        try {
            PreparedStatement pst = cn.prepareStatement("DELETE FROM registro_venta WHERE IDREGISTRO=" + idventa);
            pst.executeUpdate();
            MostrarDatos("");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Este registro esta siendo utilizado en otra tabla por medio de su llave primaria.");
        }
    }//GEN-LAST:event_eliminarMnActionPerformed

    private void detalleMnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detalleMnActionPerformed
        int fila = tablaVentas.getSelectedRow();
        int idventa = Integer.parseInt(tablaVentas.getValueAt(fila, 0).toString());


        JTable table = new JTable();
        
        DefaultTableModel modelo = new DefaultTableModel();
        modelo.addColumn("ALIMENTO");
        modelo.addColumn("CANTIDAD");
        modelo.addColumn("PRECIO");
        modelo.addColumn("IMPORTE");

        String sql = "SELECT IDALIMENTO,CANTIDAD,PRECIO_UNITARIO,IMPORTE FROM detalle_registro_venta";

        String[] datos = new String[4];
        try {
            Statement st = cn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while (rs.next()) {
                datos[0] = GetAlimentoNombre(rs.getInt(1));
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);

                modelo.addRow(datos);
            }
            table.setModel(modelo);
        } catch (SQLException ex) {
            System.err.println(ex);
        }
        
        JOptionPane.showMessageDialog(null, new JScrollPane(table));

        
    }//GEN-LAST:event_detalleMnActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregarBtn;
    private javax.swing.JComboBox<String> alimentosSelec;
    private javax.swing.JTextField cantidadtxt;
    private javax.swing.JComboBox<String> clientesSelect;
    private javax.swing.JMenuItem detalleMn;
    private javax.swing.JMenuItem eliminarMn;
    private javax.swing.JButton guardarBtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPopupMenu jPopupMenu1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JComboBox<String> meseroSelect;
    private javax.swing.JTable tablaDetalle;
    private javax.swing.JTable tablaVentas;
    private javax.swing.JTextField totaltxt;
    // End of variables declaration//GEN-END:variables
conectar cc = new conectar();
    Connection cn = cc.conexion();
}
