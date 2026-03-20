import application.dto.SinhVienRequest;
import application.usecases.ThemSinhVienUseCase;
import domain.repositories.ISinhVienRepository;
import infrastructure.persistence.SinhVienRepositoryImpl;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {

        ISinhVienRepository repo = new SinhVienRepositoryImpl();
        ThemSinhVienUseCase themSV = new ThemSinhVienUseCase(repo);

        try {
            SinhVienRequest request = new SinhVienRequest(
                    "SV01",
                    "Nguyen Van A",
                    "0123456789",
                    LocalDate.of(2000, 1, 1),
                    "a.com",
                    "SE01",
                    "IT"
            );

            themSV.execute(request);


            System.out.println("Danh sách sinh viên:");
            repo.findAll().forEach(System.out::println);

        } catch (Exception e) {
            System.out.println("Lỗi: " + e.getMessage());
        }
    }
}
