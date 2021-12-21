package examples.stateless;

import java.util.List;

import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

import examples.model.Department;

@Stateless
public class DepartmentService {
    @PersistenceContext(unitName="EmployeeService")
    protected EntityManager em;

    public List<Department> findAllDepartments() {
        return em.createQuery("SELECT d FROM Department d", Department.class)
                 .getResultList();
    }
}
