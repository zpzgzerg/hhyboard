package zpzgzerg.hhyboard.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import zpzgzerg.hhyboard.entity.Member;
import zpzgzerg.hhyboard.dto.member.MemberForm;
import zpzgzerg.hhyboard.dto.mapper.annotation.EncodeMapping;

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
