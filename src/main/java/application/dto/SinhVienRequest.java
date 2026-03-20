package application.dto;

import java.time.LocalDate;

public class SinhVienRequest {
    private String maSv;
    private String hoTen;
    private String sdt;
    private LocalDate ngaySinh;
    private String email;
    private String maLop;
    private String maKhoa;

    public SinhVienRequest(String maSv, String hoTen, String sdt,
                                 LocalDate ngaySinh, String email,
                                 String maLop, String maKhoa) {
        this.maSv = maSv;
        this.hoTen = hoTen;
        this.sdt = sdt;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.maLop = maLop;
        this.maKhoa = maKhoa;
    }

    public String getMaSv() { return maSv; }
    public String getHoTen() { return hoTen; }
    public String getSdt() { return sdt; }
    public LocalDate getNgaySinh() { return ngaySinh; }
    public String getEmail() { return email; }
    public String getMaLop() { return maLop; }
    public String getMaKhoa() { return maKhoa; }
}
