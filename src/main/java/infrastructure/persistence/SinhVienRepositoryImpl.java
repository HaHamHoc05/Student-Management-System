package infrastructure.persistence;

import domain.models.SinhVien;
import domain.repositories.ISinhVienRepository;

import java.util.ArrayList;
import java.util.List;

public class SinhVienRepositoryImpl implements ISinhVienRepository {
    private List<SinhVien> danhsach = new ArrayList<>();
    @Override
    public void save(SinhVien sv) {
        danhsach.add(sv);
    }

    @Override
    public SinhVien findById(String masv) {
        for (SinhVien sv : danhsach) {
            if (sv.getMaSv().equals(masv)) {
                return sv;
            }
        }
        return null;
    }

    @Override
    public List<SinhVien> findAll() {
        return danhsach;
    }

    @Override
    public void deleteById(String masv) {
        danhsach.remove(findById(masv));
    }
}
