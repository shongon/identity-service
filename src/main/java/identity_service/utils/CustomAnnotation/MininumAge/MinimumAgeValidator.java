package identity_service.utils.CustomAnnotation.MininumAge;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.LocalDate;
import java.time.Period;

public class MinimumAgeValidator implements ConstraintValidator<MinimumAge, LocalDate> {

    private int minimumAge;

    @Override
    public void initialize(MinimumAge constraintAnnotation) {
        this.minimumAge = constraintAnnotation.value();
    }

    @Override
    public boolean isValid(LocalDate dob, ConstraintValidatorContext context) {
        if (dob == null) {
            return true; // Cho phép null, sử dụng @NotNull để kiểm tra riêng
        }

        // Tính tuổi hiện tại
        return Period.between(dob, LocalDate.now()).getYears() >= minimumAge;
    }
}
