package application.dto;

public class SinhVienResponse {
    private String maSv;
    private String hoTen;
    private String email;
    private String maLop;
    private String maKhoa;

    public SinhVienResponse(String maSv, String hoTen,
                            String email, String maLop, String maKhoa) {
        this.maSv = maSv;
        this.hoTen = hoTen;
        this.email = email;
        this.maLop = maLop;
        this.maKhoa = maKhoa;
    }

    public String getMaSv() { return maSv; }
    public String getHoTen() { return hoTen; }
    public String getEmail() { return email; }
    public String getMaLop() { return maLop; }
    public String getMaKhoa() { return maKhoa; }

}
