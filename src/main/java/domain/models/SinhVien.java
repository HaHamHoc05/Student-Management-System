package domain.models;

import java.time.LocalDate;

public class SinhVien {
    private String maSv;
    private String hoTen;
    private String sdt;
    private LocalDate ngaySinh;
    private String email;
    private String maLop;
    private String maKhoa;

    public SinhVien(String maSv, String hoTen, String sdt, LocalDate ngaySinh, String email, String maLop, String maKhoa) {
        if (maSv == null || maSv.trim().isEmpty() ) {
            throw new IllegalArgumentException("Mã SV không được để trống");
        }

        if (hoTen == null || hoTen.trim().isEmpty() ) {
            throw new IllegalArgumentException("Họ Tên không được để trống");
        }

        if (sdt == null && !sdt.matches("^0\\d{9}$") ) {
            throw new IllegalArgumentException("Số điện thoại không hợp lệ");
        }

        if (email == null || !email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            throw new IllegalArgumentException("Định dạng email không hợp lệ!");
        }

        if (ngaySinh == null) {
            throw new IllegalArgumentException("Ngày sinh không được để trống!");
        }
        if (ngaySinh.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Ngày sinh không hợp lệ (Không thể ở tương lai)!");
        }
        this.maSv = maSv;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.maLop = maLop;
        this.maKhoa = maKhoa;
    }

    public String getMaKhoa() {
        return maKhoa;
    }

    public String getMaLop() {
        return maLop;
    }

    public String getEmail() {
        return email;
    }

    public LocalDate getNgaySinh() {
        return ngaySinh;
    }

    public String getSdt() {
        return sdt;
    }

    public String getHoTen() {
        return hoTen;
    }

    public String getMaSv() {
        return maSv;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "maSv='" + maSv + '\'' +
                ", hoTen='" + hoTen + '\'' +
                ", sdt='" + sdt + '\'' +
                ", ngaySinh=" + ngaySinh +
                ", email='" + email + '\'' +
                ", maLop='" + maLop + '\'' +
                ", maKhoa='" + maKhoa + '\'' +
                '}';
    }
}
