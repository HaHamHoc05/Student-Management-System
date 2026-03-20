package application.usecases;

import application.dto.SinhVienRequest;
import domain.models.SinhVien;
import domain.repositories.ISinhVienRepository;

import java.time.LocalDate;

public class ThemSinhVienUseCase {
    private final ISinhVienRepository svRepo;

    public ThemSinhVienUseCase(ISinhVienRepository svRepo) {
        this.svRepo = svRepo;
    }

    public void execute(SinhVienRequest svReq) {
        // check masv
        if (svRepo.findById(svReq.getMaSv()) != null) {
            throw new IllegalArgumentException("Mã sinh viên đã tồn tại");
        }
        SinhVien sv = new SinhVien(
                svReq.getMaSv(),
                svReq.getHoTen(),
                svReq.getSdt(),
                svReq.getNgaySinh(),
                svReq.getEmail(),
                svReq.getMaLop(),
                svReq.getMaKhoa()
        );
        svRepo.save(sv);
    }
}
