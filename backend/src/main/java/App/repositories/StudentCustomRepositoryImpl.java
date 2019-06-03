package App.repositories;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Subquery;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import App.models.PersonalData;
import App.models.Student;
import App.models.StudentYear;
import App.models.SubjectAttendance;

@Repository
public class StudentCustomRepositoryImpl implements StudentCustomRepository {

	@PersistenceContext
	EntityManager entityManager;

	@Override
	public Collection<Student> searchStudents(String firstName, String lastName, String indexNum, String enrolment,
			String avgGrade) {

		Session session = entityManager.unwrap(Session.class);
		CriteriaBuilder builder = session.getCriteriaBuilder();

		// Create CriteriaQuery
		CriteriaQuery<Student> criteria = builder.createQuery(Student.class);

		Root<Student> myObjectRoot = criteria.from(Student.class);
		ArrayList<Predicate> predicates = new ArrayList<Predicate>();

		if (firstName != null && firstName != "") {
			Join<Student, PersonalData> joinPersonalData = myObjectRoot.join("personalData");
			Predicate likeRestrictionFirstName = builder.and(builder.like(joinPersonalData.get("firstName"), "%" + firstName + "%"));
			predicates.add(likeRestrictionFirstName);
			// criteria.where(likeRestrictionFirstName);
		};
		if (lastName != null && lastName != "") {
			Join<Student, PersonalData> joinPersonalData = myObjectRoot.join("personalData");
			Predicate likeRestrictionLastName = builder.and(builder.like(joinPersonalData.get("lastName"), "%" + lastName + "%"));
			predicates.add(likeRestrictionLastName);
			// criteria.where(likeRestrictionLastName);
		};
		if (indexNum != null && indexNum != "") {
			Join<Student, StudentYear> joinStudentYear = myObjectRoot.join("studentYears");
			Predicate indexRestriction = builder.and(builder.equal(joinStudentYear.get("numIndex"), "%" + indexNum + "%"));
			predicates.add(indexRestriction);
			// criteria.where(indexRestriction);
		};
		if (enrolment != null && enrolment != "") {
			try {
				int enrolmentYear = 0;
				enrolmentYear = Integer.parseInt(enrolment);
				Join<Student, StudentYear> joinStudentYear = myObjectRoot.join("studentYears");
				Predicate enrolmentRestriction = builder.and(builder.equal(
						builder.function("YEAR", Integer.class, joinStudentYear.get("enrolmentDate")), enrolmentYear));
				predicates.add(enrolmentRestriction);
				// criteria.where(enrolmentRestriction);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
		if (avgGrade != null && avgGrade != "") {
			try {
				double avg = 0;
				avg = Double.parseDouble(avgGrade);
				Join<Student, SubjectAttendance> joinSubjAttendance = myObjectRoot.join("subjectAttendances");
				Subquery<Double> sub = criteria.subquery(Double.class);
				sub.select(builder.avg(joinSubjAttendance.get("finalGrade")));
				sub.from(SubjectAttendance.class);
				sub.having(builder.equal(myObjectRoot.get("id"), joinSubjAttendance.get("student").get("id")));
				Predicate gradeRestriction = builder.and(builder.equal(sub, avg));
				predicates.add(gradeRestriction);
				// criteria.where(gradeRestriction);
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
		//System.out.println("query " + entityManager.createQuery(criteria.where(predicates.toArray(new Predicate[0]))).unwrap(Query.class).getQueryString());
		Collection<Student> results = entityManager.createQuery(criteria.where(predicates.toArray(new Predicate[0]))).getResultList();
		return results;

	}

}
