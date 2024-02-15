// Copyright (c) 2023, Oracle and/or its affiliates.
//
// This program is free software; you can redistribute it and/or modify
// it under the terms of the GNU General Public License, version 2.0,
// as published by the Free Software Foundation.
//
// This program is also distributed with certain software (including
// but not limited to OpenSSL) that is licensed under separate terms,
// as designated in a particular file or component or in included license
// documentation.  The authors of MySQL hereby grant you an additional
// permission to link the program and your derivative works with the
// separately licensed software that they have included with MySQL.
//
// This program is distributed in the hope that it will be useful,
// but WITHOUT ANY WARRANTY; without even the implied warranty of
// MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
// GNU General Public License, version 2.0, for more details.
//
// You should have received a copy of the GNU General Public License
// along with this program; if not, write to the Free Software
// Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA 02110-1301  USA

#ifndef MYSQL_GTID_INTERNAL_PARSING_HELPERS_H
#define MYSQL_GTID_INTERNAL_PARSING_HELPERS_H

#include <cctype>

/// @addtogroup GroupLibsMysqlGtid
/// @{

namespace mysql::gtid::internal {

inline bool isspace(const char &ch) {
  return std::isspace(static_cast<unsigned char>(ch));
}
inline bool isalpha(const char &ch) {
  return (ch >= 'a' && ch <= 'z') || (ch >= 'A' && ch <= 'Z');
}
inline bool isdigit(const char &ch) { return std::isdigit(ch); }
inline char tolower(const char &ch) { return std::tolower(ch); }

}  //  namespace mysql::gtid::internal

/// @}

#endif  //  MYSQL_GTID_INTERNAL_PARSING_HELPERS_H
