package application.usecases;

import application.dto.SinhVienRequest;
import application.mapper.SinhVienMapper;
import domain.models.SinhVien;
import domain.repositories.ISinhVienRepository;


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
        SinhVien sv = SinhVienMapper.toEntity(svReq);
        svRepo.save(sv);
    }
}
