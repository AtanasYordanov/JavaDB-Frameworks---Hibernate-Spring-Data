package app.exam.repository;

import app.exam.domain.entities.Order;
import app.exam.domain.entities.OrderType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Transactional
public interface OrderRepository extends JpaRepository<Order, Integer> {

    @Query("SELECT o FROM Order AS o " +
            "JOIN o.employee AS e " +
            "WHERE o.orderType = :orderType AND " +
            "e.name = :employeeName")
    List<Order> findByEmployeeAndType(@Param("employeeName") String name, @Param("orderType")OrderType orderType);
}
