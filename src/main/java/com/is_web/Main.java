package com.is_web;
import com.is_web.entities.*;
import com.is_web.repository.*;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;


public class Main {

    private final RepositoryFactory repositoryFactory = new IsRepositoryFactory(DataLayerType.Database);

    public static void main(String args[]) throws RepositoryException, SQLException, InterruptedException {
        Main test = new Main();

        //1.x evidence produktů
        test.testInsertProduct("152165ac", 1008, 9, null, 1); //Funkce 1.1 - přidání nového zařízení do systému
        System.out.println("Probehla Funkce 1.1 - přidání nového zařízení do systému");
        System.out.println();

        System.out.println("Funkce 1.2 - výpis zaznamenaných zařízení v systému");
        System.out.println("----------------------------------------------------");
        test.testAllProducts(); //Funkce 1.2 - výpis zaznamenaných zařízení v systému
        System.out.println("----------------------------------------------------");
        System.out.println();

        System.out.println("Funkce 1.3 - výpis detailů daného zařízení");
        System.out.println("----------------------------------------------------");
        test.testDetailProduct(8); //Funkce 1.3 - výpis detailů daného zařízení
        System.out.println("----------------------------------------------------");
        System.out.println();

        test.testUpdateProduct(8); //Funkce 1.4 - editace uloženého zařízení
        System.out.println("Proběhla Funkce 1.4 - editace uloženého zařízení");
        System.out.println();

        test.testDeleteProduct(1024); //Funkce 1.5 - odstranění uloženého zařízení
        System.out.println("Proběhla Funkce 1.5 - odstranění uloženého zařízení");
        System.out.println();

        //Funkce 2.x evidence nákupních faktur
        test.testInsertInvoiceBought(); //Funkce 2.1 - přidání nové nákupní faktury
        System.out.println("Proběhla Funkce 2.1 - přidání nové nákupní faktury");
        System.out.println();

        System.out.println("Funkce 2.2 - výpis nákupních faktur");
        System.out.println("----------------------------------------------------");
        test.testAllInvoiceBought(); //Funkce 2.2 - výpis nákupních faktur
        System.out.println("----------------------------------------------------");
        System.out.println();

        System.out.println("Funkce 2.3 - detail vybrané uložené nákupní faktury");
        System.out.println("----------------------------------------------------");
        test.testDetailInvoiceBought(1); //Funkce 2.3 - detail vybrané uložené nákupní faktury
        System.out.println("----------------------------------------------------");
        System.out.println();

        test.testDeleteInvoiceBought(2); //Funkce 2.4 - smazání nákupní faktury
        System.out.println("Proběhla Funkce 2.4 - smazání nákupní faktury");
        System.out.println();

        //Funkce 3.x evidence prodejních faktur
        test.testInsertInvoiceSold(); //Funkce 3.1 - přidání nové prodejní faktury
        System.out.println("Proběhla Funkce 3.1 - přidání nové prodejní faktury");
        System.out.println();

        System.out.println("Funkce 3.2 - výpis prodejních faktur");
        System.out.println("----------------------------------------------------");
        test.testAllInvoiceSold(); //Funkce 3.2 - výpis prodejních faktur
        System.out.println("----------------------------------------------------");
        System.out.println();

        System.out.println("Funkce 3.3 - výpis faktur vztahujících se k vybrané firmě");
        System.out.println("----------------------------------------------------");
        test.testSpecificInvoiceSold(3); //Funkce 3.3 - výpis faktur vztahujících se k vybrané firmě
        System.out.println("----------------------------------------------------");
        System.out.println();

        System.out.println("Funkce 3.4 - detail vybrané uložené prodejní faktury");
        System.out.println("----------------------------------------------------");
        test.testDetailInvoiceSold(1); //Funkce 3.4 - detail vybrané uložené prodejní faktury
        System.out.println("----------------------------------------------------");
        System.out.println();

        test.testDeleteInvoiceSold(2); //Funkce 3.5 - smazání prodejní faktury
        System.out.println("Proběhla Funkce 3.5 - smazání prodejní faktury");
        System.out.println();

        //Funkce 4.x evidence zaměstnanců
        test.testInsertEmployee("dummy", 1); //Funkce 4.1 (Transakce) - vytvoření nového zaměstnance
        System.out.println("Proběhla Funkce 4.1 (Transakce) - vytvoření nového zaměstnance");
        System.out.println();

        System.out.println("Funkce 4.2 - výpis zaměstnanců");
        System.out.println("----------------------------------------------------");
        test.testAllEmployee(); //Funkce 4.2 - výpis zaměstnanců
        System.out.println("----------------------------------------------------");
        System.out.println();

        System.out.println("Funkce 4.3 - detail vybraného zaměstnance");
        System.out.println("----------------------------------------------------");
        test.testDetailEmployee(1); //Funkce 4.3 - detail vybraného zaměstnance
        System.out.println("----------------------------------------------------");
        System.out.println();

        System.out.println("Funkce 4.4 - Výpis přiřazených zařízení vybraného zaměstnance");
        System.out.println("----------------------------------------------------");
        test.testSpecificEmployeesWithDevices(2); //Funkce 4.4 - Výpis přiřazených zařízení vybraného zaměstnance
        System.out.println("----------------------------------------------------");
        System.out.println();

        test.testUpdateEmployees(1); //Funkce 4.5 (Transakce) - editace vybraného zaměstnance
        System.out.println("Proběhla Funkce 4.5 (Transakce) - editace vybraného zaměstnance");
        System.out.println();

        test.testDeleteEmployee(1007);  //Funkce 4.6 (Transakce) - smazání zaměstnance
        System.out.println("Proběhla Funkce 4.6 (Transakce) - smazání zaměstnance");
        System.out.println();

        //Funkce 5.x evidence lokalit
        test.testInsertLocality(); //Funkce 5.1 (Transakce) - vytvoření nové pracovní lokality
        System.out.println("Proběhla Funkce 5.1 (Transakce) - vytvoření nové pracovní lokality");
        System.out.println();

        System.out.println("Funkce 5.2 - výpis pracovních lokalit");
        System.out.println("----------------------------------------------------");
        test.testAllLocality(); //Funkce 5.2 - výpis pracovních lokalit
        System.out.println("----------------------------------------------------");
        System.out.println();

        System.out.println("Funkce 5.3 - detail vybrané lokality");
        System.out.println("----------------------------------------------------");
        test.testDetailLocality(1); //Funkce 5.3 - detail vybrané lokality
        System.out.println("----------------------------------------------------");
        System.out.println();

        test.testUpdateLocality(1); //Funkce 5.4 - editace vybrané lokality
        System.out.println("Proběhla Funkce 5.4 - editace vybrané lokality");
        System.out.println();

        test.testDeleteLocality(2); //Funkce 5.5 - smazání lokality
        System.out.println("Proběhla Funkce 5.5 - smazání lokality");
        System.out.println();

        System.out.println("Funkce 5.6 - Zobrazení všech zaměstnanců vybrané lokality");
        System.out.println("----------------------------------------------------");
        test.testFindEmployeesOfTheLocality(1); //Funkce 5.6 - Zobrazení všech zaměstnanců vybrané lokality
        System.out.println("----------------------------------------------------");
        System.out.println();

        //Funkce 6.x evidence firem
        test.testInsertCompany(); //Funkce 6.1 - vytvoření nového záznamu firmy
        System.out.println("Proběhla Funkce 6.1 - vytvoření nového záznamu firmy");
        System.out.println();

        System.out.println("Funkce 6.2 - výpis záznamu firem");
        System.out.println("----------------------------------------------------");
        test.testAllCompanies(); //Funkce 6.2 - výpis záznamu firem
        System.out.println("----------------------------------------------------");
        System.out.println();

        System.out.println("Funkce 6.3 - výpis detailu vybrané firmy");
        System.out.println("----------------------------------------------------");
        test.testDetailCompany(1); //Funkce 6.3 - výpis detailu vybrané firmy
        System.out.println("----------------------------------------------------");
        System.out.println();

        test.testUpdateCompany(1); //Funkce 6.4 - editace vybrané firmy
        System.out.println("Proběhla Funkce 6.4 - editace vybrané firmy");
        System.out.println();

        test.testDeleteCompany(2); //Funkce 6.5 - smazání firmy
        System.out.println("Proběhla Funkce 6.5 - smazání firmy");
        System.out.println();

        //Funkce 7.x evidence reklamací
        test.testInsertReclamation(); //Funkce 7.1 - vytvoření nové reklamace
        System.out.println("Proběhla Funkce 7.1 - vytvoření nové reklamace");
        System.out.println();

        System.out.println("Funkce 7.2 - výpis všech reklamací");
        System.out.println("----------------------------------------------------");
        test.testAllReclamations(); //Funkce 7.2 - výpis všech reklamací
        System.out.println("----------------------------------------------------");
        System.out.println();

        System.out.println("Funkce 7.3 - výpis všech reklamací vybrané firmy");
        System.out.println("----------------------------------------------------");
        test.testListOfReclamationsOfCertainCompany(1);  //Funkce 7.3 - výpis všech reklamací vybrané firmy
        System.out.println("----------------------------------------------------");
        System.out.println();

        System.out.println("Funkce 7.4 - detail vybrané reklamace");
        System.out.println("----------------------------------------------------");
        test.testDetailReclamations(2); //Funkce 7.4 - detail vybrané reklamace
        System.out.println("----------------------------------------------------");
        System.out.println();

        test.testUpdateReclamations(2); //Funkce 7.5 - editace vybrané reklamace
        System.out.println("Proběhla Funkce 7.5 - editace vybrané reklamace");
        System.out.println();

        test.testFinishReclamations(7); //Funkce 7.6 -- uzavření reklamace
        System.out.println("Proběhla Funkce 7.6 -- uzavření reklamace");
        System.out.println();

        test.testDeleteReclamations(2); //Funkce 7.7 - smazání reklamace
        System.out.println("Proběhla Funkce 7.7 - smazání reklamace");
        System.out.println();

        test.testNotificationReclamations("2021-12-31"); //Funkce 7.8 - upozornění o uzavření reklamace
        System.out.println("Proběhla Funkce 7.8 - upozornění o uzavření reklamace");
        System.out.println();

        //Funkce 8.x evidence typů set-top boxů
        test.testInsertStb(); //Funkce 8.1 - vytvoření nového typu set-top boxu
        System.out.println("Proběhla Funkce 8.1 - vytvoření nového typu set-top boxu");
        System.out.println();

        System.out.println("Proběhla Funkce 8.2 - výpis všech typů set-top boxů");
        System.out.println("----------------------------------------------------");
        test.testAllStb();  //Funkce 8.2 - výpis všech typů set-top boxů
        System.out.println("----------------------------------------------------");
        System.out.println();

        System.out.println("Funkce 8.3 - výpis vybraného typu set-top boxu");
        System.out.println("----------------------------------------------------");
        test.testDetailStb(1010); //Funkce 8.3 - výpis vybraného typu set-top boxu
        System.out.println("----------------------------------------------------");
        System.out.println();
    }

    //Funkce 1.1 - přidání nového zařízení do systému
    public void testInsertProduct(String serialNumber, Integer employeeId, int stbType, Integer invoiceSold, int invoiceBought) throws RepositoryException {
        Repository<Product> productRepository = repositoryFactory.create(Product.class);
        Product newProduct = new Product(serialNumber, employeeId, stbType, invoiceSold, invoiceBought);
        productRepository.saveOrUpdate(newProduct);
    }

    //Funkce 1.2 - výpis zaznamenaných zařízení v systému
    public void testAllProducts() throws RepositoryException {
        Repository<Product> productRepository = repositoryFactory.create(Product.class);
        List<Product> productList = productRepository.getAll();
        for (Product p : productList) {
            if (p.getIs_deleted() == false) {
                System.out.println(p.toString());
            }
        }
    }

    //Funkce 1.3 - výpis detailů daného zařízení
    public void testDetailProduct(int id) throws RepositoryException {
        Repository<Product> productRepository = repositoryFactory.create(Product.class);
        Product p = productRepository.findById(id);
        if (p.getIs_deleted() == false) {
            System.out.println(p.toString());
        }
    }

    //Funkce 1.4 - editace uloženého zařízení
    public void testUpdateProduct(int id) throws RepositoryException {
        Repository<Product> productRepository = repositoryFactory.create(Product.class);
        Product testProduct = productRepository.findById(id);
        productRepository.saveOrUpdate(testProduct);
    }

    //Funkce 1.5 - odstranění uloženého zařízení
    public void testDeleteProduct(int id) throws RepositoryException {
        Repository<Product> productRepository = repositoryFactory.create(Product.class);
        Product testProduct = productRepository.findById(id);
        productRepository.delete(testProduct);
    }

    //Funkce 2.1 - přidání nové nákupní faktury
    public void testInsertInvoiceBought() throws RepositoryException {
        Repository<Invoice_bought> invoice_boughtRepository = repositoryFactory.create(Invoice_bought.class);
        Invoice_bought newInvoiceBought = new Invoice_bought("1353", 1, 1500, 1, 9);
        invoice_boughtRepository.saveOrUpdate(newInvoiceBought);
    }

    //Funkce 2.2 - výpis nákupních faktur
    public void testAllInvoiceBought() throws RepositoryException {
        Repository<Invoice_bought> invoice_boughtRepository = repositoryFactory.create(Invoice_bought.class);
        List<Invoice_bought> invoiceBoughtList = invoice_boughtRepository.getAll();
        for (Invoice_bought ib : invoiceBoughtList) {
            if (ib.getIs_deleted() == false) {
                System.out.println(ib.toString());
            }
        }
    }

    //Funkce 2.3 - detail vybrané uložené nákupní faktury
    public void testDetailInvoiceBought(int id) throws RepositoryException {
        Repository<Invoice_bought> invoice_boughtRepository = repositoryFactory.create(Invoice_bought.class);
        Invoice_bought ib = invoice_boughtRepository.findById(id);
        if (ib.getIs_deleted() == false) {
            System.out.println(ib.toString());
        }
    }

    //Funkce 2.4 - smazání nákupní faktury
    public void testDeleteInvoiceBought(int id) throws RepositoryException {
        Repository<Invoice_bought> invoice_boughtRepository = repositoryFactory.create(Invoice_bought.class);
        Invoice_bought testInvoiceBought = invoice_boughtRepository.findById(id);
        invoice_boughtRepository.delete(testInvoiceBought);
    }

    //Funkce 3.1 - přidání nové prodejní faktury
    public void testInsertInvoiceSold() throws RepositoryException {
        Repository<Invoice_sold> invoice_soldRepository = repositoryFactory.create(Invoice_sold.class);
        Invoice_sold newInvoiceSold = new Invoice_sold("1364", 1, 1500, 1, 9);
        invoice_soldRepository.saveOrUpdate(newInvoiceSold);
    }

    //Funkce 3.2 - výpis prodejních faktur
    public void testAllInvoiceSold() throws RepositoryException {
        Repository<Invoice_sold> invoice_soldRepository = repositoryFactory.create(Invoice_sold.class);
        List<Invoice_sold> invoiceSoldList = invoice_soldRepository.getAll();
        for (Invoice_sold is : invoiceSoldList) {
            if (is.getIs_deleted() == false) {
                System.out.println(is.toString());
            }
        }
    }

    //Funkce 3.3 - výpis faktur vztahujících se k vybrané firmě
    public void testSpecificInvoiceSold(int id) throws RepositoryException {
        Repository<Invoice_sold> invoice_soldRepository = repositoryFactory.create(Invoice_sold.class);
        List<Invoice_sold> invoice_soldList = invoice_soldRepository.where("companies_id", id);
        for (Invoice_sold i : invoice_soldList) {
            if (i.getIs_deleted() == false) {
                System.out.println(i.toString());
            }
        }
    }

    //Funkce 3.4 - detail vybrané uložené prodejní faktury
    public void testDetailInvoiceSold(int id) throws RepositoryException {
        Repository<Invoice_sold> invoice_soldRepository = repositoryFactory.create(Invoice_sold.class);
        Invoice_sold is = invoice_soldRepository.findById(id);
        if (is.getIs_deleted() == false) {
            System.out.println(is);
        }
    }

    //Funkce 3.5 - smazání prodejní faktury
    public void testDeleteInvoiceSold(int id) throws RepositoryException {
        Repository<Invoice_sold> invoice_soldRepository = repositoryFactory.create(Invoice_sold.class);
        Invoice_sold testInvoiceSold = invoice_soldRepository.findById(id);
        invoice_soldRepository.delete(testInvoiceSold);
    }

    //Funkce 4.1 (Transakce) - vytvoření nového zaměstnance
    public void testInsertEmployee(String testInsert, int localityId) throws RepositoryException, SQLException {
        Repository<Employee> employeeRepository = repositoryFactory.create(Employee.class);
        Employee newEmployee = new Employee(testInsert, testInsert, testInsert, testInsert, testInsert, testInsert, testInsert, 1, false);
        employeeRepository.execTransactionCreateNewEmployee(newEmployee);
    }

    //Funkce 4.2 - výpis zaměstnanců
    public void testAllEmployee() throws RepositoryException {
        Repository<Employee> employeeRepository = repositoryFactory.create(Employee.class);
        List<Employee> employeeList = employeeRepository.getAll();
        for (Employee e : employeeList) {
            if (e.getIsDeleted() == false) {
                System.out.println(e.toString());
            }
        }
    }

    //Funkce 4.3 - detail vybraného zaměstnance
    public void testDetailEmployee(int id) throws RepositoryException {
        Repository<Employee> employeeRepository = repositoryFactory.create(Employee.class);
        Employee e = employeeRepository.findById(id);
        if (e.getIsDeleted() == false) {
            System.out.println(e.toString());
        }
    }

    //Funkce 4.4 - Výpis přiřazených zařízení vybraného zaměstnance
    public void testSpecificEmployeesWithDevices(int id) throws RepositoryException {
        Repository<Product> productRepository = repositoryFactory.create(Product.class);
        List<Product> productList = productRepository.where("employees_id", id);
        for (Product p : productList) {
            if (p.getIs_deleted() == false) {
                System.out.println(p.toString());
            }
        }
    }

    //Funkce 4.5 (Transakce) - editace vybraného zaměstnance
    public void testUpdateEmployees(int employeeId) throws RepositoryException, SQLException {
        Repository<Employee> employeeRepository = repositoryFactory.create(Employee.class);
        Employee e = employeeRepository.findById(employeeId);
        e.setName("pokus");
        employeeRepository.execTransactionUpdateEmployee(e);
    }

    //Funkce 4.6 (Transakce) - smazání zaměstnance
    public void testDeleteEmployee(int employeeId) throws RepositoryException, SQLException {
        Repository<Employee> employeeRepository = repositoryFactory.create(Employee.class);
        Employee e = employeeRepository.findById(employeeId);
        employeeRepository.execTransactionDeleteEmployee(e);
    }

    //Funkce 5.1 (Transakce) - vytvoření nové pracovní lokality
    public void testInsertLocality() throws RepositoryException, SQLException {
        Repository<Locality> localityRepository = repositoryFactory.create(Locality.class);
        Locality newLocality = new Locality("DUMMYtest", "DUMMY", "DUMMY", "DUMMY", "DUMMY");
        localityRepository.execTransactionCreateNewLocality(newLocality);
    }

    //Funkce 5.2 - výpis pracovních lokalit
    public void testAllLocality() throws RepositoryException {
        Repository<Locality> localityRepository = repositoryFactory.create(Locality.class);
        System.out.println(localityRepository.getAll().toString());
        List<Locality> localityList = localityRepository.getAll();
        for (Locality l : localityList) {
            if (l.getIs_deleted() == false) {
                System.out.println(l.toString());
            }
        }
    }

    //Funkce 5.3 - detail vybrané lokality
    public void testDetailLocality(int id) throws RepositoryException {
        Repository<Locality> localityRepository = repositoryFactory.create(Locality.class);
        Locality locality = localityRepository.findById(id);
        if (locality.getIs_deleted() == false) {
            System.out.println(locality.toString());
        }
    }

    //Funkce 5.4 - editace vybrané lokality
    public void testUpdateLocality(int id) throws RepositoryException {
        Repository<Locality> localityRepository = repositoryFactory.create(Locality.class);
        Locality testLocality = localityRepository.findById(id);
        testLocality.setEmployees_count(0);
        localityRepository.saveOrUpdate(testLocality);
    }

    //Funkce 5.5 - smazání lokality
    public void testDeleteLocality(int id) throws RepositoryException {
        Repository<Locality> localityRepository = repositoryFactory.create(Locality.class);
        Locality testLocality = localityRepository.findById(id);
        localityRepository.delete(testLocality);
    }

    //Funkce 5.6 - Zobrazení všech zaměstnanců vybrané lokality
    public void testFindEmployeesOfTheLocality(int locality_id) throws RepositoryException {
        Repository<Employee> employeeRepository = repositoryFactory.create(Employee.class);
        List<Employee> employeeList = employeeRepository.where("locality_id", locality_id);
        for (Employee e : employeeList) {
            if (e.getIsDeleted() == false) {
                System.out.println(e);
            }
        }
    }

    //Funkce 6.1 - vytvoření nového záznamu firmy
    public void testInsertCompany() throws RepositoryException {
        Repository<Company> companyRepository = repositoryFactory.create(Company.class);
        Company newCompany = new Company("DUMMYtest", "DUMMY", "DUMMY", "DUMMY", "DUMMY", "DUMMY", "DUMMY");
        companyRepository.saveOrUpdate(newCompany);
    }

    //Funkce 6.2 - výpis záznamu firem
    public void testAllCompanies() throws RepositoryException {
        Repository<Company> companyRepository = repositoryFactory.create(Company.class);
        List<Company> companyList = companyRepository.getAll();
        for (Company c : companyList) {
            if (c.getIs_deleted() == false) {
                System.out.println(c.toString());
            }
        }
    }

    //Funkce 6.3 - výpis detailu vybrané firmy
    public void testDetailCompany(int id) throws RepositoryException {
        Repository<Company> companyRepository = repositoryFactory.create(Company.class);
        Company c = companyRepository.findById(id);
        if (c.getIs_deleted() == false) {
            System.out.println(c.toString());
        }
    }

    //Funkce 6.4 - editace vybrané firmy
    public void testUpdateCompany(int id) throws RepositoryException {
        Repository<Company> companyRepository = repositoryFactory.create(Company.class);
        Company testCompany = companyRepository.findById(id);
        testCompany.setEmail("test");
        testCompany.setName("Marlon");
        companyRepository.saveOrUpdate(testCompany);
    }

    //Funkce 6.5 - smazání firmy
    public void testDeleteCompany(int id) throws RepositoryException {
        Repository<Company> companyRepository = repositoryFactory.create(Company.class);
        Company testCompany = companyRepository.findById(id);
        companyRepository.delete(testCompany);
    }

    //Funkce 7.1 - vytvoření nové reklamace
    public void testInsertReclamation() throws RepositoryException {
        Repository<Reclamation> reclamationsRepository = repositoryFactory.create(Reclamation.class);
        Reclamation newReclamation = new Reclamation("200200215", false, LocalDate.parse("2021-05-20"), LocalDate.parse("2021-05-23"), "overheating", 1015, 1);
        reclamationsRepository.saveOrUpdate(newReclamation);
    }

    //Funkce 7.2 - výpis všech reklamací
    public void testAllReclamations() throws RepositoryException {
        Repository<Reclamation> reclamationsRepository = repositoryFactory.create(Reclamation.class);
        List<Reclamation> reclamationList = reclamationsRepository.getAll();
        for (Reclamation r : reclamationList) {
            if (r.getIs_deleted() == false) {
                System.out.println(r.toString());
            }
        }
    }

    //Funkce 7.3 - výpis všech reklamací vybrané firmy
    public void testListOfReclamationsOfCertainCompany(int id) throws RepositoryException {
        Repository<Reclamation> reclamationRepository = repositoryFactory.create(Reclamation.class);
        List<Reclamation> reclamationList = reclamationRepository.getAll();
        for (Reclamation r : reclamationList) {
            if (r.getIs_deleted() == false && r.getCompanies_id() == id) {
                System.out.println(r.toString());
            }
        }
    }

    //Funkce 7.4 - detail vybrané reklamace
    public void testDetailReclamations(int id) throws RepositoryException {
        Repository<Reclamation> reclamationsRepository = repositoryFactory.create(Reclamation.class);
        Reclamation reclamation = reclamationsRepository.findById(id);
        if (reclamation.getIs_deleted() == false) {
            System.out.println(reclamation.toString());
        }
    }

    //Funkce 7.5 -- editace vybrané reklamace
    public void testUpdateReclamations(int id) throws RepositoryException {
        Repository<Reclamation> reclamationsRepository = repositoryFactory.create(Reclamation.class);
        Reclamation testReclamation = reclamationsRepository.findById(id);
        LocalDate date = LocalDate.parse("2021-09-01");
        testReclamation.setEnd_date(date);
        reclamationsRepository.saveOrUpdate(testReclamation);
    }

    //Funkce 7.6 - uzavření reklamace
    public void testFinishReclamations(int id) throws RepositoryException {
        Repository<Reclamation> reclamationsRepository = repositoryFactory.create(Reclamation.class);
        Reclamation testReclamation = reclamationsRepository.findById(id);
        testReclamation.setApproved(true);
        reclamationsRepository.saveOrUpdate(testReclamation);
    }

    //Funkce 7.7 - smazání reklamace
    public void testDeleteReclamations(int id) throws RepositoryException {
        Repository<Reclamation> reclamationsRepository = repositoryFactory.create(Reclamation.class);
        Reclamation testReclamation = reclamationsRepository.findById(id);
        reclamationsRepository.delete(testReclamation);
    }

    //Funkce 7.8 - upozornění o uzavření reklamace
    public void testNotificationReclamations(String date) throws RepositoryException {
        Repository<Reclamation> reclamationsRepository = repositoryFactory.create(Reclamation.class);
        LocalDate dateToTest = LocalDate.parse(date);
        List<Reclamation> reclamationList = reclamationsRepository.where("end_date", dateToTest, WhereOperator.LessOrEqual);
        sendEmails(reclamationList);
    }

    //Funkce 7.8.1 - pomocná funkce k odeslání emailu na ukončené reklamace
    public void sendEmails(List<Reclamation> reclamationList) throws RepositoryException {
        Repository<Company> companyRepository = repositoryFactory.create(Company.class);
        String from = "sender@abc.com";
        String host = "127.0.0.1";
        for (Reclamation reclamation : reclamationList){
            if (reclamation.getIs_deleted() == false) {
                String to = companyRepository.findById(reclamation.getCompanies_id()).getEmail();
                String message = "Your RMA " + reclamation.getId() + " with s/n " + reclamation.getSerial_number() + " has been repaired.";
                System.out.println(message);
                //Odeslání zprávy
            }
        }
    }

    //Funkce 8.1 - vytvoření nového typu set-top boxu
    public void testInsertStb() throws RepositoryException {
        Repository<StbType> stbTypeRepository = repositoryFactory.create(StbType.class);
        StbType testStb = new StbType("Android TV", 2, "Arduino", 2, 1);
        stbTypeRepository.saveOrUpdate(testStb);
    }

    //Funkce 8.2 - výpis všech typů set-top boxů
    public void testAllStb() throws RepositoryException {
        Repository<StbType> stbTypeRepository = repositoryFactory.create(StbType.class);
        List<StbType> stbTypeList = stbTypeRepository.getAll();
        for (StbType s : stbTypeList) {
            if (s.getIs_deleted() == false) {
                System.out.println(s.toString());
            }
        }
    }

    //Funkce 8.3 - editace vybraného typu set-top boxu
    public void testDetailStb(int id) throws RepositoryException {
        Repository<StbType> stbTypeRepository = repositoryFactory.create(StbType.class);
        StbType stbType = stbTypeRepository.findById(id);
        if (stbType.getIs_deleted() == false) {
            System.out.println(stbType.toString());
        }
    }
}