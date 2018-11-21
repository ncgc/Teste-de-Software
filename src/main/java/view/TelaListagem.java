package view;

import java.util.ArrayList;

import model.Contato;
import model.ServicoContatoImpl;
import model.ServicoUsuarioImpl;
import model.Usuario;

public class TelaListagem extends javax.swing.JFrame {
    private Usuario usuario;
    private ArrayList<Contato> buscados;
    private int indice;
    private Contato contato;
    private ServicoContatoImpl servico;
    private Boolean busca;

     public TelaListagem(Usuario usuario) {
        this.usuario = usuario;
        this.buscados = null;
        this.busca = false;
        setup();
        initComponents();
        this.listarContatos();
    }

    public TelaListagem(Usuario usuario, ArrayList<Contato> buscados){
        this.usuario = usuario;
        this.buscados = buscados;
        this.busca = true;
        setup();
        initComponents();
        this.listarContatos();
    }

    public void setup(){
        String nomeArquivo = new ServicoUsuarioImpl().getNomeArquivo(usuario);
        servico = new ServicoContatoImpl(nomeArquivo);
    }

    @SuppressWarnings("unchecked")

    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        list1 = new java.awt.List();
        btnEditar = new javax.swing.JButton();
        btnExcluir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jLabel1.setText("Contatos existentes na agenda:");

        list1.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                list1ItemStateChanged(evt);
            }
        });

        btnEditar.setText("Editar");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        btnExcluir.setText("Excluir");
        btnExcluir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExcluirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(33, 33, 33)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnEditar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnExcluir))
                    .addComponent(jLabel1)
                    .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 306, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(22, 22, 22)
                .addComponent(list1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(23, 23, 23)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditar)
                    .addComponent(btnExcluir))
                .addContainerGap(32, Short.MAX_VALUE))
        );

        pack();
    }

    private void listarContatos() {
        if (busca == false)
            buscados = (ArrayList<Contato>) servico.listarTodosContatos();
        if(buscados != null){
            for(Contato c: buscados){
                list1.add(c.tupla());
            }
        }
    }    
    
  

    private void list1ItemStateChanged(java.awt.event.ItemEvent evt) {
        indice = list1.getSelectedIndex();
        contato = getContatoLista(indice);
    }

    public Contato getContatoLista(int indice){
        String[] partes = list1.getItem(indice).split(";", 4);
        return new Contato(partes[0], partes[1], partes[2], partes[3]);
    }

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {
        new TelaCadastro(usuario, contato).setVisible(true);
        this.setVisible(false);
    }

    private void btnExcluirActionPerformed(java.awt.event.ActionEvent evt){
        boolean status = servico.removerContato(contato);
        if(status)
            list1.remove(indice);
    }

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        new TelaPrincipal(usuario).setVisible(true);
    }


    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnExcluir;
    private javax.swing.JLabel jLabel1;
    private java.awt.List list1;
    // End of variables declaration//GEN-END:variables
}
