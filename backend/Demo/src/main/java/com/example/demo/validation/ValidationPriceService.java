package com.example.demo.validation;
import java.math.BigDecimal;
public class ValidationPriceService {
	public void validatePrice(BigDecimal price) {
        if (price == null) {
            throw new IllegalArgumentException("Giá không được để trống.");
        }
        
        BigDecimal remainder = price.remainder(new BigDecimal("100"));
        if (remainder.compareTo(BigDecimal.ZERO) != 0) {
            throw new IllegalArgumentException("Giá tiền phải là bội số của 100 (ví dụ: 10.500, 603.100).");
        }

        // Nếu bạn muốn chặt chẽ hơn, chỉ cho phép x.x00 (tức chia hết cho 1000)
//         BigDecimal remainder1000 = price.remainder(new BigDecimal("1000"));
//         if (remainder1000.compareTo(BigDecimal.ZERO) != 0) {
//             throw new IllegalArgumentException("Giá tiền phải là bội số của 1000 (ví dụ: 10.000, 603.000).");
//         }
    }
}
