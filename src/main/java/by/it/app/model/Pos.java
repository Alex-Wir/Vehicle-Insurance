package by.it.app.model;

import javax.persistence.*;
import java.util.Set;

/**
 * Class for represent pos information
 */
@Entity
@Table
public class Pos {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String number;

    @OneToMany(mappedBy = "pos")
    private Set<Shift> shifts;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "point_id", nullable = false)
    private Point point;

    @Override
    public String toString() {
        return "Pos{" +
                "id=" + id +
                ", number='" + number + '\'' +
                ", point=" + point +
                '}';
    }

    public Point getPoint() {
        return point;
    }

    public void setPoint(Point point) {
        this.point = point;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public Set<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(Set<Shift> shifts) {
        this.shifts = shifts;
    }
}
