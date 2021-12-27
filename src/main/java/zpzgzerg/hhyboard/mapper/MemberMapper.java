package zpzgzerg.hhyboard.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import zpzgzerg.hhyboard.entity.Member;
import zpzgzerg.hhyboard.form.member.MemberForm;
import zpzgzerg.hhyboard.mapper.annotation.EncodeMapping;

@Mapper(componentModel = "spring", uses = PasswordEncoderMapper.class)
public interface MemberMapper {

    @Mapping(source = "password", target="password", qualifiedBy = EncodeMapping.class)
    @Mapping(target = "boards", ignore = true)
    @Mapping(target = "comments", ignore = true)
    @Mapping(target = "points", ignore = true)
    Member formToMember(MemberForm form);

    @Mapping(source = "id", target = "memberId")
    MemberForm memberToForm(Member member);

}
