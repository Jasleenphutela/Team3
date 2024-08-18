package in.co.hsbc.ecommerceApp.beanfactory;

import in.co.hsbc.ecommerceApp.adminController.AdminController;
import in.co.hsbc.ecommerceApp.adminDao.AdminDao;
import in.co.hsbc.ecommerceApp.adminDao.Impl.AdminJdbcDaoImpl;
import in.co.hsbc.ecommerceApp.service.AdminService;
import in.co.hsbc.ecommerceApp.service.Impl.AdminServiceImpl;

public class BeanFactory {
        private AdminDao dao;
        private AdminService service;
        private AdminController controller;

        public BeanFactory(){
            dao=new AdminJdbcDaoImpl();
            service=new AdminServiceImpl(dao);
            controller=new AdminController(service);
        }

        public AdminController getController(){
            return controller;
        }
    }

