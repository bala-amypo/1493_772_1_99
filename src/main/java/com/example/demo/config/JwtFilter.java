public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;

    public JwtFilter(JwtUtil jwtUtil) {
        this.jwtUtil = jwtUtil;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain)
            throws ServletException, IOException {

        String header = request.getHeader("Authorization");

        if (header != null && header.startsWith("Bearer ")) {
            String token = header.substring(7);

            if (jwtUtil.validateToken(token)) {
                Claims claims = jwtUtil.getClaims(token);

                String email = claims.get("email", String.class);

                @SuppressWarnings("unchecked")
                List<String> roles = (List<String>) claims.get("roles");

                List<SimpleGrantedAuthority> authorities =
                        roles.stream()
                             .map(r -> new SimpleGrantedAuthority("ROLE_" + r))
                             .toList();

                UsernamePasswordAuthenticationToken authentication =
                        new UsernamePasswordAuthenticationToken(
                                email, null, authorities);

                SecurityContextHolder.getContext()
                                     .setAuthentication(authentication);
            }
        }

        chain.doFilter(request, response);
    }
}
