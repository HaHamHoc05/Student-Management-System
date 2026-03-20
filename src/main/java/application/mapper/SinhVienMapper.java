package application.mapper;

import application.dto.SinhVienRequest;
import application.dto.SinhVienResponse;
import domain.models.SinhVien;

public class SinhVienMapper {
    public static SinhVien toEntity(SinhVienRequest request) {
        return new SinhVien(
                request.getMaSv(),
                request.getHoTen(),
                request.getSdt(),
                request.getNgaySinh(),
                request.getEmail(),
                request.getMaLop(),
                request.getMaKhoa()
        );
    }

    public static SinhVienResponse toResponse(SinhVien sv) {
        return new SinhVienResponse(
                sv.getMaSv(),
                sv.getHoTen(),
                sv.getEmail(),
                sv.getMaLop(),
                sv.getMaKhoa()
        );
    }

}
