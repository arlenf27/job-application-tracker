#include <string>
#include <optional>
#include <vector>

using namespace std;

namespace job_app_regex{
	enum employee_type{
		EMPLOYEE = 0,
		INTERN = 1,
		FREELANCER = 2,
		CONTRACTOR = 3
	};

	enum salary_type{
		HOURLY = 0,
		ANNUAL = 1,
		MONTHLY = 2,
		WEEKLY = 3
	};

	struct app_fields{
		optional<string> req_id;
		optional<string> job_title;
		optional<vector<string>> locations;
		optional<employee_type> emp_type;
		optional<bool> sponsor_eligible;
		optional<unsigned int> min_salary;
		optional<unsigned int> max_salary;
		optional<salary_type> sal_type;
		optional<unsigned int> start_month;
		optional<unsigned int> start_year;
		optional<unsigned int> end_month;
		optional<unsigned int> end_year;
		optional<string> basic_qualifications;
		optional<string> preferred_qualifications;
	};
}
